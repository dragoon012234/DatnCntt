package com.bkdn.cntt.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkdn.cntt.entities.InterestedTopicEntity;
import com.bkdn.cntt.entities.PostEntity;
import com.bkdn.cntt.entities.ThemeEntity;
import com.bkdn.cntt.entities.TopicEntity;
import com.bkdn.cntt.models.Post;
import com.bkdn.cntt.models.Theme;
import com.bkdn.cntt.models.Topic;
import com.bkdn.cntt.models.general.ErrorResponse;
import com.bkdn.cntt.models.general.ServerErrorCode;
import com.bkdn.cntt.repositories.InterestedTopicRepository;
import com.bkdn.cntt.repositories.PostRepository;
import com.bkdn.cntt.repositories.ThemeRepository;
import com.bkdn.cntt.repositories.TopicRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ThemeRepository themeRepo;
	@Autowired
	private TopicRepository topicRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private InterestedTopicRepository inTopicRepo;

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
		var es = topicRepo.findAllByTheme(theme);
		ArrayList<Topic> ms = new ArrayList<Topic>(es.size());
		for (var e : es) {
			ms.add(new Topic(e));
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
			ms.add(new Post(e));
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
		return new Post(e);
	}

	@Override
	public Boolean subscriber(Integer user, Integer topic, Boolean interested) {
		var e = new InterestedTopicEntity(0, topic, user);
		if (interested) {
			try {
				e = inTopicRepo.save(e);
			} catch (Exception ex) {
			}
		} else {
			try {
				e = inTopicRepo.findByUserAndTopic(user, topic);
				inTopicRepo.deleteById(e.id);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	@Override
	public ArrayList<Topic> getLastTopic(Integer count) {
		return null;
	}

	@Override
	public void deleteUser(Integer user) {
		inTopicRepo.deleteAllByUser(user);
		topicRepo.deleteAllByUser(user);
		postRepo.deleteAllByUser(user);
	}

}
