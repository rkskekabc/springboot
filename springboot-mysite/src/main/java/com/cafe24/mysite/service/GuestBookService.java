package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> showList() {
		return guestBookDao.getList();
	}
	
	public void write(GuestBookVo guestBookVo) {
		guestBookDao.insert(guestBookVo);
	}
	
	public void delete(GuestBookVo guestBookVo) {
		guestBookDao.delete(guestBookVo);
	}
	
	
}
