
    * 发表微博
    * @param microBlog
    * @return
    */
   public boolean post(final MicroBlog microBlog){
       
      String sql_postBlog = "insert into microblog (m_id,u_id,m_content,m_releasetime,m_transfrom,m_transamount,m_image) values(null,?,?,?,?,?,?)";
      DBConn dbConn = new DBConn();
      int u_id = microBlog.getU_id();
      String m_content = microBlog.getM_content();
      String m_releasetime = microBlog.getM_releasetime();
      String m_transfrom = microBlog.getM_transfrom();
      long m_transamount = microBlog.getM_transamount();
      String m_image = microBlog.getM_image();
      int affectedRows = dbConn.execOther(sql_postBlog, new Object[]{u_id,m_content,m_releasetime,m_transfrom,m_transamount,m_image});
      dbConn.closeConn();
      return (affectedRows>0)?true:false;
   }
   /**
    * 是否转发
    * @param m_id_in
    * @param u_id_in
    * @return
    */
   public boolean transmit(final int m_id_in,final int u_id_in){
      String sql_tansmit = "insert into microblog (u_id,m_content,m_releasetime,m_transfrom,m_transamount,m_image) values(?,?,?,?,?,?)";
      String sql_gettransmit = "select * from microblog where m_id = ?";
      String sql_updateold = "update microblog set m_transamount=m_transamount+1 where m_id = ?";
      
      int u_id = 0;
      String m_content =null;
      String m_releasetime = null;
      String m_image = null;
      String m_transfrom = null;
      long m_transamount = 0;
      
      
      
      DBConn dbConn = new DBConn();
      ResultSet rs = dbConn.execQuery(sql_gettransmit, new Object[]{m_id_in});
      try {
         while(rs.next()){
            u_id = rs.getInt("u_id");
            m_content = rs.getString("m_content");
            
            Calendar c = Calendar.getInstance();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            m_releasetime =  f.format(c.getTime());
            
            m_image = rs.getString("m_image");
            
            UserDao userDao = new UserDao();
            String u_nickname = userDao.getUserNameById(u_id);
            m_transfrom = rs.getString("m_transfrom")+"@"+u_nickname;
            
            m_transamount = rs.getLong("m_transamount")+1;
         }
      } catch (SQLException e) {
         
         e.printStackTrace();
      }finally{
         try {
            rs.close();
         } catch (SQLException e) {
            
            e.printStackTrace();
         }
      }
      int affectedRows2= dbConn.execOther(sql_updateold, new Object[]{m_id_in});
      int affectedRows = dbConn.execOther(sql_tansmit, new Object[]{u_id_in,m_content,m_releasetime,m_transfrom,m_transamount,m_image});
      dbConn.closeConn();
      return (affectedRows>0&&affectedRows2>0)?true:false;
      
   }