package com.omniwyse.sms.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Messages;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.utils.MessagesDTO;
import com.omniwyse.sms.utils.MessagesDetails;

@Service
public class MessagesService {
	@Autowired
	private DatabaseRetrieval retrive;
	private Database db;

	public int sendMessage(MessagesDTO messagesDTO, long tenantId, String sentflag) {
		if (messagesDTO.getMessage() != null) {
			if (messagesDTO.getStudents()!= null || messagesDTO.getTeachers() != null
					|| messagesDTO.getStudentid() != 0 || messagesDTO.getTeacherid() != 0) {
				db = retrive.getDatabase(tenantId);
				Timestamp today = new Timestamp(System.currentTimeMillis());
				Messages message = new Messages();
				message.setMessagedate(today);
				message.setMessage(messagesDTO.getMessage());
				message.setSentflag(sentflag);
				if (sentflag == "T") {
					message.setTeacherid(messagesDTO.getTeacherid());
					if (messagesDTO.getId() != 0) {
						message.setStudentid(messagesDTO.getStudentid());
						message.setRootmessageid(messagesDTO.getId());
						return db.insert(message).getRowsAffected();
					}
					ArrayList<Long> studentslist = messagesDTO.getStudents();
					for (Long studentid : studentslist) {
						message.setStudentid(studentid);
						db.insert(message);

					}
				} else {

					message.setStudentid(messagesDTO.getStudentid());
					if (messagesDTO.getId() != 0) {
						message.setTeacherid(messagesDTO.getStudentid());
						message.setRootmessageid(messagesDTO.getId());
						return db.insert(message).getRowsAffected();
					}
					ArrayList<Long> teachers = messagesDTO.getTeachers();

					for (Long teacher : teachers) {
						message.setTeacherid(teacher);
						db.insert(message);
					}
				}
				return 1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}

	}

	public List<MessagesDetails> teacherSentMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);

		List<MessagesDetails> messages = db.sql(
				"select messages.id,messages.message,messages.messagedate,students.name,students.fathername,students.mothername "
						+ "from messages " + " join students on students.id=messages.studentid "
						+ " where messages.sentflag='T' and messages.teacherid=? order by messages.messagedate desc ",
				messagesDTO.getTeacherid()).results(MessagesDetails.class);
		for (MessagesDetails message : messages) {
			List<MessagesDetails> replymessages = db
					.sql("select messages.id,messages.messagedate,messages.message,messages.studentid,messages.teacherid,"
							+ "students.name,students.fathername,students.mothername " + "from messages "
							+ "join students on students.id=messages.studentid "
							+ "where messages.sentflag='P' and messages.rootmessageid=?", message.getId())
					.results(MessagesDetails.class);

			message.setReplymessages(replymessages);
		}

		return messages;
	}

	public List<MessagesDetails> parentSentMessages(MessagesDTO messagesDTO, long tenantId) {

		db = retrive.getDatabase(tenantId);

		List<MessagesDetails> messages = db.sql(
				"select messages.id,messages.message,messages.messagedate,messages.teacherid,messages.studentid,teachers.teachername "
						+ "from messages " + "join teachers on teachers.id=messages.teacherid "
						+ "where messages.sentflag='P' and messages.studentid=? order by messages.messagedate desc",
				messagesDTO.getStudentid()).results(MessagesDetails.class);
		for (MessagesDetails message : messages) {
			List<MessagesDetails> replymessages = db
					.sql(" select messages.id,messages.messagedate,messages.message,messages.studentid,messages.teacherid,teachers.teachername "
							+ "from messages " + "join teachers on teachers.id=messages.teacherid "
							+ "where messages.sentflag='T' and messages.rootmessageid=?", message.getId())
					.results(MessagesDetails.class);
			message.setReplymessages(replymessages);
		}
		return messages;
	}

	public List<MessagesDetails> teacherRecievedMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.sql(
				"select messages.id,messages.message,messages.messagedate,messages.rootmessageid,messages.studentid,messages.teacherid,"
						+ "students.name,students.fathername,students.mothername " + " from messages "
						+ "join students on students.id=messages.studentid " + " where sentflag='p' and teacherid=?",
				messagesDTO.getTeacherid()).results(MessagesDetails.class);
	}

	public List<MessagesDetails> parentRecievedMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db
				.sql("select messages.id,messages.message,messages.messagedate,messages.rootmessageid,messages.studentid,messages.teacherid,teachers.teachername "
						+ "from messages " + "join teachers on teachers.id=messages.teacherid "
						+ " where sentflag='T' and studentid=?", messagesDTO.getStudentid())
				.results(MessagesDetails.class);
	}

	public List<Teachers> liststudentclassandclasssubjectteacher(long studentid, long tenantId) {
		db = retrive.getDatabase(tenantId);
		Teachers classteacher = db.sql("select teachers.id,teachers.teachername " + "from teachers"
				+ " join classrooms on classrooms.classteacherid=teachers.id "
				+ " join classroom_students on classroom_students.classid=classrooms.id "
				+ " where classroom_students.studentid=?", studentid).results(Teachers.class).get(0);
		List<Teachers> teachers = db.sql(" select teachers.id,teachers.teachername " + "from teachers "
				+ "join class_subject_teacher on class_subject_teacher.teacherid=teachers.id "
				+ "join classroom_students on classroom_students.classid=class_subject_teacher.classid "
				+ "where classroom_students.studentid=?", studentid).results(Teachers.class);
		teachers.add(classteacher);
		return teachers;
	}

}
