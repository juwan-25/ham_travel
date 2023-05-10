# ham_travel
### JAVA GUI를 활용한 햄스터 슈팅 게임
jPannel을 이용한 화면 구현과 DB 개발을 하였습니다. <br>
픽셀을 통해 직접 디자인 요소를 제작하였습니다. <br>



#### 기능 구현 코드 설명
<details>
<summary>DB</summary>
  
``` java
  try {
     String url = "jdbc:mysql://localhost:3306/ham_schema";
     String user = "root";
     String passwd = "mirim";
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");

     } catch (ClassNotFoundException e) {
     }
     con = DriverManager.getConnection(url,user, passwd);
     System.out.println("DB연결 성공");
  } catch (SQLException e) {
     System.out.println("DB연결 실패");
     System.out.print("사유 : " + e.getMessage());
  }

```
jdbc 사용을 위한 코드입니다. <br>
협업 과정에서 jdbc 연동이 되지 않아 가장 어려웠던 부분입니다. <br><br>
 
``` java  
PreparedStatement ps= null;
try {
   ps = db.getCon().prepareStatement("insert into ham_score(userName, userScore) " +
           "values('"+textName.getText()+"', "+score
+");");
   ps.executeUpdate();
} catch (SQLException ex) {
   ex.printStackTrace();
}
```
insert 문을 활용하여 점수를 받아온 후 화면에 띄워지게 하고 이름을 입력받았습니다. <br><br>
 
``` java  
Statement st = db.getCon().createStatement();
ResultSet resultSet = st.executeQuery("SELECT * FROM " +
       "( SELECT * FROM ham_score ORDER BY userScore DESC )A " +
       "LIMIT 5");

int i=0;
while(resultSet.next()){
   String name = resultSet.getString("userName");

   nameArr[i] = new JLabel(name);
   nameArr[i].setFont(font.deriveFont(Font.BOLD, 40));
   nameArr[i].setBounds(315, 270+70*i, 150, 60);
   nameArr[i].setHorizontalAlignment(JLabel.CENTER);
   panel.add(nameArr[i]);

   i++;
}
```
select 문을 활용하여 DB에 저장되어있는 값 중 상위 5개의 값을 가져와 출력하였습니다. <br><br>
</details>

<details>
<summary>화면 구현</summary>
 
``` java
JTextField textName = new JTextField();
textName.setBounds(560, 305, 230, 50);
textName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
textName.setFont(font.deriveFont(Font.BOLD, 30));

JButton btnSave = new JButton(iconSave);
btnIntro.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
       new Intro();
       setVisible(false);
   }
});
```
Java GUI를 처음 사용해보아서 미숙한 부분이 많았습니다. <br>
jPannel을 이용하여 화면을 띄운 후 button과 textfield를 통해 화면을 구성하였습니다. <br>
</details>
