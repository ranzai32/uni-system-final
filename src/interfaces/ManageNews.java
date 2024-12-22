package interfaces;

import enums.*;
import common.*;
public interface ManageNews {
public void makeNew(String title, String topic, String content, Languages language);
public void deleteNew(News n);
public void deleteComment(Comment c);


/*
 * package common;

import enums.Languages;
import database.DataBase;
import users.*;

public abstract class ManageNews  {
	public void makeNew(String title, String topic, String content, Languages language, User author) {
		News news = new News(title, topic, content, language, author);
	}
	public void deleteNew(News n) {
		DataBase.getInstance().deleteNew(n);
	}
	public void deleteComment(Comment c) {
		if (c != null){
			News news = c.getNews();
				news.removeComment(c);
				System.out.println("Комментарий удалён.");
		}
		else{
			System.out.println("Комментарий не найден.");
		}
	}
}

 */
}


