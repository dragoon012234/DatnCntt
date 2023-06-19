package com.bkdn.cntt.models;

import java.util.ArrayList;

import com.bkdn.cntt.entities.InterestedTopicEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InterestedTopic {

	public Integer user;
	public ArrayList<Integer> topics;

	public InterestedTopic() {
	}
	
	public InterestedTopic(Integer user, ArrayList<Integer> topics) {
		this.user = user;
		this.topics = topics;
	}
	
	public InterestedTopic(ArrayList<InterestedTopicEntity> es) {
		this.user = ((InterestedTopicEntity)es.toArray()[0]).user;
		this.topics = new ArrayList<Integer>(es.size());
		for (InterestedTopicEntity e : es) {
			this.topics.add(e.topic);
		}
	}

}
