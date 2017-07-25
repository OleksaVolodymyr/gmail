package com.epam.model;

public class MessageModel {

	private String fromWho;
	private String subject;
	private String message;

	public MessageModel(String fromWho, String subject, String message) {
		this.fromWho = fromWho;
		this.subject = subject;
		this.message = message;
	}

	public String getFromWho() {
		return this.fromWho;
	}

	public void setFromWho(String fromWho) {
		this.fromWho = fromWho;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.fromWho == null) ? 0 : this.fromWho.hashCode());
		result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
		result = prime * result + ((this.subject == null) ? 0 : this.subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageModel other = (MessageModel) obj;
		if (this.fromWho == null) {
			if (other.fromWho != null)
				return false;
		} else if (!this.fromWho.equals(other.fromWho))
			return false;
		if (this.message == null) {
			if (other.message != null)
				return false;
		} else if (!this.message.equals(other.message))
			return false;
		if (this.subject == null) {
			if (other.subject != null)
				return false;
		} else if (!this.subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageModel [fromWho=" + this.fromWho + ", subject=" + this.subject + ", message=" + this.message
				+ "]\n";
	}
}
