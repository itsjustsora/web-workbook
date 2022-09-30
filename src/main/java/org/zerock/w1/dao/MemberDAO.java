package org.zerock.w1.dao;

import lombok.Cleanup;
import org.zerock.w1.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

    public MemberVO getWithPassword(String mid, String mpw) throws Exception {
        String query = "SELECT mid, mpw, mname FROM tbl_member WHERE mid = ? AND mpw = ?";

        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, mid);
        statement.setString(2, mpw);

        @Cleanup ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        memberVO = MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .mname(resultSet.getString(3))
                .build();

        return memberVO;
    }

    public void updateUuid(String mid, String uuid) throws Exception {
        String sql = "UPDATE tbl_member SET uuid = ? WHERE mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, uuid);
        statement.setString(2, mid);

        statement.executeUpdate();
    }

    public MemberVO selectUUID(String uuid) throws Exception {
        String query = "SELECT mid, mpw, mname, uuid FROM tbl_member WHERE uuid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, uuid);

        @Cleanup ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        return MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .mname(resultSet.getString(3))
                .uuid(resultSet.getString(4))
                .build();
    }
}