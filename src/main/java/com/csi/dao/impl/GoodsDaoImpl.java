package com.csi.dao.impl;

import com.csi.dao.GoodsDao;
import com.csi.domain.Goods;
import com.csi.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public int insert(Goods g) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into goods(name,image,price) value(?,?,?)";
        PreparedStatement psmt = DBUtil.getPstmt(conn,sql,g.getName(),g.getImage(),g.getPrice());
        int i = DBUtil.update(psmt);
        DBUtil.close(conn,psmt,null);
        return i;

    }

    @Override
    public List<Goods> selectAll() {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from goods";
        PreparedStatement psmt = DBUtil.getPstmt(conn,sql);
        ResultSet rs = DBUtil.query(psmt);
        List<Goods> list = new ArrayList<>();
        try {
            while (rs.next()){
                list.add(new Goods(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                        )
                );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
