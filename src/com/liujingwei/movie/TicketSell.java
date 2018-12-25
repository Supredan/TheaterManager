package com.liujingwei.movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TicketSell{
    private Window window;
    private JButton btn_hold;
    private JButton btn_relese;
    private static JTextArea area;

    int com_room,com_row,com_col;
    boolean flag ;
    public TicketSell(){
        Window window = new Window();
        btn_hold = window.getButtonHold();
        btn_relese = window.getButtonRelese();
        area = window.getJTextArea();
        btn_hold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com_room = Integer.parseInt(window.getField1Text());
                com_col = Integer.parseInt(window.getField3Text());
                com_row = Integer.parseInt(window.getField2Text());
                flag = compareAll(com_col,com_row,com_room);
                if(flag){
                    insert(com_room,com_row,com_col);
                    selectAll();
                    area.append("\r\n"+com_room+" 号放映厅 "+com_row +" 行 "+com_col+" 座成功售出。");
                }else {
                    area.append("\r\n"+com_room+" 号放映厅 "+com_row +" 行 "+com_col+" 座已售出！！！");
                }
            }
        });
        btn_relese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com_room = Integer.parseInt(window.getField1Text());
                com_col = Integer.parseInt(window.getField3Text());
                com_row = Integer.parseInt(window.getField2Text());
                flag = compareAll(com_col,com_row,com_room);
                if(!flag){
                    delete(com_col,com_row,com_room);
                    area.append("\r\n"+com_room+" 号放映厅 "+com_row +" 行 "+com_col+" 座已清空，可以出售。");
                    selectAll();
                }else{
                    area.append("\r\n"+com_room+" 号放映厅 "+com_row +" 行 "+com_col+" 座未出售，无需清空！！！");
                }
            }
        });
    }

    public static void main(String[] args) {
        new TicketSell();
    }


    public static Boolean compareAll(int com_col,int com_row,int com_room){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select * from seat";
            preparedStatement= connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                if(resultSet.getInt(2)==com_room&&resultSet.getInt(3)==com_row
                    &&resultSet.getInt(4)==com_col){
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return true;
    }

    public static void selectAll(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select * from seat";
            preparedStatement= connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+"\t"+resultSet.getInt(2)+"\t"
                        +resultSet.getInt(3)+"\t"+resultSet.getInt(4)
                        +"\t"+resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
    }


    public static void insert(int room,int row,int col){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into seat(room,row,col) values (?,?,?)";
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,room);
            pstm.setInt(2,row);
            pstm.setInt(3,col);

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet,pstm,connection);
        }
    }

    public static void update(int id,boolean occupy){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "update seat set ocuppy = ? where id = ?";
            pstm = connection.prepareStatement(sql);

            pstm.setInt(2,id);
            pstm.setBoolean(1,occupy);

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet,pstm,connection);
        }
    }

    public static void delete(int com_col,int com_row,int com_room){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "delete from seat where room = ? and row = ? and col = ?";
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,com_room);
            pstm.setInt(2,com_row);
            pstm.setInt(3,com_col);

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet,pstm,connection);
        }
    }

}
