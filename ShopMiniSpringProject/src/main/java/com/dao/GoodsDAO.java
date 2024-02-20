package com.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;

@Repository
public class GoodsDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = session.selectList("goodsList", gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO goods = session.selectOne("goodsRetrieve", gCode);
		return goods;
	}

	public void cartAdd(CartDTO cart) {
		session.insert("cartAdd", cart);
	}

	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = session.selectList("selectCartList", userid);
		return list;
	}

	public void cartDelete(String num) {
		session.delete("deleteCartByNum", num);
	}

	public void cartUpdate(Map<String, String> map) {
		session.update("amountUpdate", map);
	}

	public void delAllCart(List<String> list) {
		session.delete("cartDelAll", list);
	}
}
