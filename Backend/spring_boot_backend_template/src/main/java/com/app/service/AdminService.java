package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import com.app.dto.AddTrekRequest;
import com.app.dto.AddTrekResponse;

public interface AdminService {
	AddTrekResponse addTrekDetails(AddTrekRequest addtrekDTO,MultipartFile imageFile) throws IOException;
}
