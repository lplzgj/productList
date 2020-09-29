package com.csi.service.impl;

import com.csi.dao.GoodsDao;
import com.csi.dao.impl.GoodsDaoImpl;
import com.csi.domain.Goods;
import com.csi.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public String addGoods(Goods g) {
        return goodsDao.insert(g)>0?"��ӳɹ�":"���ʧ��";
    }

    @Override
    public List<Goods> findAll() {
        return goodsDao.selectAll();
    }
}
