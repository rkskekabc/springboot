package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.util.Pager;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private Pager pager;
	
	@Autowired
	private BoardDao boardDao;
	
	public BoardVo show(Long no) {
		BoardVo vo = boardDao.get(no);
		vo.setHit(vo.getHit() + 1);
		boardDao.hitUpdate(vo);
		return boardDao.get(no);
	}
	
	public List<BoardVo> showList(int currentPage, String keyword){
		pager.init(5, 5, boardDao.getCount(keyword), currentPage);
		int offset = pager.getCurrentPage() * pager.getCountPerPage();
		int limit = pager.getCountPerPage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("limit", limit);
		map.put("keyword", keyword);
		return boardDao.getList(map);
	}
	
	public Pager getPager() {
		return pager;
	}
	
	public void write(BoardVo vo) {
		vo.setGroupNo(boardDao.getMaxGroupNo() + 1);
		boardDao.insert(vo);
	}
	
	public void modify(BoardVo vo) {
		boardDao.update(vo);
	}
	
	public void delete(Long no) {
		boardDao.delete(no);
	}
	
	public void reply(BoardVo vo) {
		List<BoardVo> updateList = boardDao.getReplyList(vo);
		for(BoardVo updateVo : updateList) {
			updateVo.setOrderNo(updateVo.getOrderNo() + 1);
			boardDao.replyUpdate(updateVo);
		}
		boardDao.insert(vo);
	}
}
