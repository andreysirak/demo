package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.entity.Comment;
import com.entity.Reply;

//@Component
public class CommentDAOImpl implements CommentDAO {
	
	
	static ArrayList<Comment> listOfComments = new ArrayList<Comment>();
	
	static {
		listOfComments.add(new Comment((long) 1, (long) 221, "Comment_1", new ArrayList<Reply>()));
		listOfComments.add(new Comment((long) 1, (long) 222, "Comment_2", new ArrayList<Reply>()));
		listOfComments.add(new Comment((long) 2, (long) 224, "never do it))", new ArrayList<Reply>()));
		listOfComments.add(new Comment((long) 2, (long) 225, "better learn python", new ArrayList<Reply>()));
		
		Comment commentWithReplies = new Comment((long) 1, (long) 223, "Comment_3", new ArrayList<Reply>());
		
		ArrayList<Reply> listOfReplies123_1 = new ArrayList<Reply>();
		
		
		listOfReplies123_1.add(new Reply("reply_1"));	
		listOfReplies123_1.add(new Reply("reply_2"));
		commentWithReplies.setListOfReplies(listOfReplies123_1);
		listOfComments.add(commentWithReplies);
		
		
	}
	
	@Override
	public List<Comment> findByTopicId(Long topicId) {		
		
		ArrayList<Comment> listOfFoundComments = new ArrayList<Comment>();
		for (Comment comment : listOfComments) {
			if(comment.getTopicId().equals(topicId)) {
				listOfFoundComments.add(comment);
			}
		}
		
		// TODO Auto-generated method stub
		return listOfFoundComments;
	}

	@Override
	public void addComment(Comment comment) {
		listOfComments.add(comment);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void addReply(Long commentId, Reply reply) {
//		for (Comment comment : listOfComments) {
//			if(comment.getId().equals(commentId)) {
//				comment.getListOfReplies().add(reply);				
//			}
//			
//		}
		
		
		
//	}
	
	
	
	

}
