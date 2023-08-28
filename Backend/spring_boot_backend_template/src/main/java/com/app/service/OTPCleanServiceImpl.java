package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OtpRepository;

@Service
public class OTPCleanServiceImpl implements OTPCleanService {
	private final OtpRepository otpDao;
	
	@Autowired
    public OTPCleanServiceImpl(OtpRepository otpDao) { //,TaskScheduler taskScheduler
        this.otpDao = otpDao;
//        this.taskScheduler = taskScheduler;
    }
	
	
	
	@Transactional//@Scheduled(cron = "0 */5 * * * *")// Execute daily at midnight
	@Scheduled(fixedRate = 10000)// 5 minutes in milliseconds (fixedDelay = 5 * 60 * 1000)
	@Override
	public void cleanupExpiredOtpRecords() {
		otpDao.deleteExpiredRecords();
	}


}
