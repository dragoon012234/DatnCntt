package com.bkdn.cntt.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkdn.cntt.entities.AccountEntity;
import com.bkdn.cntt.entities.InterestedTopicEntity;
import com.bkdn.cntt.entities.LikedPostEntity;
import com.bkdn.cntt.entities.NotificationEntity;
import com.bkdn.cntt.entities.PostEntity;
import com.bkdn.cntt.entities.ThemeEntity;
import com.bkdn.cntt.entities.TopicEntity;
import com.bkdn.cntt.enums.NotificationType;
import com.bkdn.cntt.models.InterestedTopic;
import com.bkdn.cntt.models.LikedPost;
import com.bkdn.cntt.models.Notification;
import com.bkdn.cntt.models.Post;
import com.bkdn.cntt.models.Theme;
import com.bkdn.cntt.models.Topic;
import com.bkdn.cntt.models.general.ErrorResponse;
import com.bkdn.cntt.models.general.ServerErrorCode;
import com.bkdn.cntt.repositories.AccountRepository;
import com.bkdn.cntt.repositories.InterestedTopicRepository;
import com.bkdn.cntt.repositories.LikedPostRepository;
import com.bkdn.cntt.repositories.NotificationRepository;
import com.bkdn.cntt.repositories.PostRepository;
import com.bkdn.cntt.repositories.ThemeRepository;
import com.bkdn.cntt.repositories.TopicRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private ThemeRepository themeRepo;
	@Autowired
	private TopicRepository topicRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private InterestedTopicRepository inTopicRepo;
	@Autowired
	private LikedPostRepository likePostRepo;
	@Autowired
	private NotificationRepository notiRepo;

	@Override
	public ArrayList<Theme> getAllTheme() {
		var es = themeRepo.findAll();
		ArrayList<Theme> ms = new ArrayList<Theme>(es.size());
		for (var e : es) {
			ms.add(new Theme(e));
		}
		return ms;
	}

	@Override
	public ArrayList<Topic> getAllTopic(Integer theme) {
		ArrayList<TopicEntity> es = null;
		if (theme != null) {
			es = topicRepo.findAllByTheme(theme);
		} else {
			es = topicRepo.findAll();
		}
		ArrayList<Topic> ms = new ArrayList<Topic>(es.size());
		for (var e : es) {
			var m = new Topic(e);
			m.configure(getAllSubscriber(e.id));
			ms.add(m);
		}
		return ms;
	}

	@Override
	public ArrayList<Post> getAllPost(Integer topic) {
		ArrayList<PostEntity> es = null;
		if (topic != null) {
			es = postRepo.findAllByTopic(topic);
		} else {
			es = postRepo.findAll();
		}
		ArrayList<Post> ms = new ArrayList<Post>(es.size());
		for (var e : es) {
			var m = new Post(e);
			m.configure(getAllLiker(e.id));
			ms.add(m);
		}
		return ms;
	}

	@Override
	public Theme create(Theme theme) {
		var e = new ThemeEntity(theme);
		e = themeRepo.save(e);
		return new Theme(e);
	}

	@Override
	public Topic create(Topic topic, Post post) {
		var e = new TopicEntity(topic);
		e.createTime = System.currentTimeMillis();
		e.closed = false;
		e = topicRepo.save(e);
		var e2 = new PostEntity(post);
		e2.createTime = System.currentTimeMillis();
		e2.topic = e.id;
		e2 = postRepo.save(e2);
		var watcheres = accRepo.findAllByWatcher(true);
		for (AccountEntity accountEntity : watcheres) {
			var noti = new NotificationEntity(null, e.theme, e.id, e2.id, topic.user, accountEntity.id,
					NotificationType.Creation, System.currentTimeMillis(), false);
			notiRepo.save(noti);
		}
		return new Topic(e);
	}

	@Override
	public Topic update(Topic topic, Boolean check) throws Exception {
		var op = topicRepo.findById(topic.id);
		if (op.isEmpty()) {
			throw new ErrorResponse(ServerErrorCode.Unauthorized, "Unauthorized");
		}
		var e = op.get();
		if (check) {
			if (e.user != topic.user) {
				throw new ErrorResponse(ServerErrorCode.Unauthorized, "Unauthorized");
			}
		}
		e.topic = topic.topic;
		e.closed = topic.closed;
		e = topicRepo.save(e);
		return new Topic(e);
	}

	@Override
	public Post create(Post post) {
		var e = new PostEntity(post);
		e.createTime = System.currentTimeMillis();
		e = postRepo.save(e);
		var op = topicRepo.findById(e.topic);
		if (op.isPresent()) {
			var notis = new ArrayList<NotificationEntity>();
			var e2 = op.get();
			notis.add(new NotificationEntity(null, e2.theme, e2.id, e.id, e.user, e2.user, NotificationType.TopicReply,
					System.currentTimeMillis(), false));

			var is = getAllSubscriber(e2.id);
			for (InterestedTopic i : is) {
				notis.add(new NotificationEntity(null, e2.theme, e2.id, e.id, e.user, i.user,
						NotificationType.TopicReply, System.currentTimeMillis(), false));
			}

			if (e.replyOn != null) {
				var opReply = postRepo.findById(e.replyOn);
				if (opReply.isPresent()) {
					var eReply = opReply.get();
					notis.add(new NotificationEntity(null, e2.theme, e2.id, e.id, e.user, eReply.user,
							NotificationType.PostReply, System.currentTimeMillis(), false));
				}
			}
			notiRepo.saveAll(notis);
		}
		return new Post(e);
	}

	@Override
	public Boolean subscriber(Integer user, Integer topic, Boolean interested) {
		var e = inTopicRepo.findByUserAndTopic(user, topic);
		if (e == null) {
			e = new InterestedTopicEntity(null, topic, user, interested, System.currentTimeMillis());
		} else {
			e.interested = interested;
		}
		e = inTopicRepo.save(e);
		return true;
	}

	@Override
	public Boolean like(Integer user, Integer post, Boolean liked) {
		var e = likePostRepo.findByPostAndUser(post, user);
		if (e == null) {
			e = new LikedPostEntity(null, post, user, liked, System.currentTimeMillis());
		} else {
			e.liked = liked;
		}
		e = likePostRepo.save(e);
		return true;
	}

	@Override
	public ArrayList<Notification> getAllNotification(Integer user) {
		var es = notiRepo.findAllByUser(user);
		var ms = new ArrayList<Notification>();
		for (NotificationEntity e : es) {
			ms.add(new Notification(e));
		}
		return ms;
	}

	@Override
	public void deleteUser(Integer user) {
		inTopicRepo.deleteAllByUser(user);
		likePostRepo.deleteAllByUser(user);
		notiRepo.deleteAllByUser(user);
		topicRepo.deleteAllByUser(user);
		postRepo.deleteAllByUser(user);
	}

	public ArrayList<InterestedTopic> getAllSubscriber(Integer topic) {
		var es = inTopicRepo.findByTopicAndInterested(topic, true);
		var ms = new ArrayList<InterestedTopic>();
		for (InterestedTopicEntity e : es) {
			ms.add(new InterestedTopic(e));
		}
		return ms;
	}

	public ArrayList<LikedPost> getAllLiker(Integer post) {
		var es = likePostRepo.findByPostAndLiked(post, true);
		var ms = new ArrayList<LikedPost>();
		for (LikedPostEntity e : es) {
			ms.add(new LikedPost(e));
		}
		return ms;
	}

}
