package com.bkdn.cntt.services;

import java.util.ArrayList;

import com.bkdn.cntt.models.Notification;
import com.bkdn.cntt.models.Post;
import com.bkdn.cntt.models.Theme;
import com.bkdn.cntt.models.Topic;

public interface PostService {

	public ArrayList<Theme> getAllTheme();

	public ArrayList<Topic> getAllTopic(Integer theme);

	public ArrayList<Post> getAllPost(Integer topic);

	public Theme create(Theme theme);

	public Topic create(Topic topic, Post post);

	public Topic update(Topic topic, Boolean check) throws Exception;

	public Post create(Post post);

	public Boolean subscriber(Integer user, Integer topic, Boolean interested);

	public Boolean like(Integer user, Integer post, Boolean liked);

	public ArrayList<Notification> getAllNotification(Integer user);

	public void deleteUser(Integer user);

}
