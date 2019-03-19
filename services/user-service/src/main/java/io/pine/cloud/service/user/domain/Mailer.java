package io.pine.cloud.service.user.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Mailer {
	private String from;
	private String to;
	private String subject;
	private String text;
	private List<Attachment> attachments = new ArrayList<Attachment>();

	@Data
	public static class Attachment{
		private String name;
		private String path;
		
		public Attachment(String name, String path){
			this.name = name;
			this.path = path;
		}
	}
}
