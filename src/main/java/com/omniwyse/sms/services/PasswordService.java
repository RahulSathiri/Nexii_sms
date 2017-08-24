package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.utils.PasswordDTO;

@Service
public class PasswordService {
	@Autowired
	private DatabaseRetrieval retrive;
	private Database db;

	public int changePassword(long tenantId, PasswordDTO passwordDTO) {
		if (passwordDTO.getMail() != null && passwordDTO.getPassword() != null
				&& passwordDTO.getNewpassword() != null) {
			if (!passwordDTO.getPassword().equals(passwordDTO.getNewpassword())) {
				db = retrive.getDatabase(tenantId);
				List<UserCredentials> user = db
						.where("mail=? and password=?", passwordDTO.getMail(), passwordDTO.getPassword())
						.results(UserCredentials.class);
				if (!user.isEmpty()) {
					return db.sql("update usercredentials set password=? where mail=?", passwordDTO.getNewpassword(),
							passwordDTO.getMail()).execute().getRowsAffected();

				} else
					return 0;
			} else
				return -5;

		} else
			return -1;

	}

}
