package com.app.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.app.dto.AddTrekRequest;
import com.app.dto.AddTrekResponse;
import com.app.dto.AdminSignInRequest;
import com.app.dto.AdminSignInResponse;

public interface AdminService {
	AddTrekResponse addTrekDetails(AddTrekRequest addtrekDTO,MultipartFile imageFile) throws IOException;
	AdminSignInResponse singInAdmin(AdminSignInRequest adminDTO);
}
