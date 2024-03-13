package cn.smbms.filter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

//数据类型为想要实现的数据类型
public class DateTypeHandler extends BaseTypeHandler<String> {
    //将java类型 转化成 数据库需要的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String date, JdbcType jdbcType) throws SQLException {


    }

    //将数据库中类型转换成java类型
    //String参数 要转换的字段名称
    //ResultSet 查询出的结果集
    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Date getDate = new Date(resultSet.getDate(s).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(getDate);
        return simpleDateFormat.format(getDate);
    }

    //将数据库中类型转换成java类型
    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Date getDate = new Date(resultSet.getDate(i).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(getDate);
        return simpleDateFormat.format(getDate);
    }

    //将数据库中类型转换成java类型
    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Date getDate = new Date(callableStatement.getDate(i).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(getDate);
        return simpleDateFormat.format(getDate);
    }
}
