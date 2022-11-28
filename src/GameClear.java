import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GameClear extends JFrame {
    int width, height;
    Toolkit tk = Toolkit.getDefaultToolkit();

    Font font;
    int clearscore;

    // 게임 점수 전달
    public void setClearscore(int clearscore) {
        this.clearscore = clearscore;
    }

    Image backGround = new ImageIcon("src/img/back_gameclear.png").getImage();

    public void init() { // 컴포넌트 세팅
        width = 1200;
        height = 800;

    }

    public GameClear() throws IOException, FontFormatException {
        super("clear"); // 타이틀
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();

        Dimension screen = tk.getScreenSize();
        int xpos = (int) (screen.getWidth() / 2 - width / 2);
        int ypos = (int) (screen.getHeight() / 2 - height / 2);
        setLocation(xpos, ypos);

        MyPanel panel = new MyPanel();
        panel.setLayout(null);

        // 외부 글꼴 적용
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/font/dunggeunmo.ttf"));

            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        JButton btnIntro = new JButton("메인으로");
        btnIntro.setBounds(250, 530, 230, 80);
        btnIntro.setFont(font.deriveFont(Font.BOLD, 40));
        btnIntro.setHorizontalAlignment(JLabel.CENTER);
        btnIntro.setBorderPainted(false);
        btnIntro.setContentAreaFilled(false);
        btnIntro.setFocusPainted(false);

        JButton btnSave = new JButton("저장하기");
        btnSave.setBounds(710, 530, 230, 80);
        btnSave.setFont(font.deriveFont(Font.BOLD, 40));
        btnSave.setHorizontalAlignment(JLabel.CENTER);
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setFocusPainted(false);

        panel.add(btnIntro);
        panel.add(btnSave);


        btnIntro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Intro();
                setVisible(false); // 창 안보이게 하기
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Input input = new Input();
                    // 게임 점수 전달
                    input.labelGetScore.setText(Integer.toString(clearscore));
                    input.setScore(clearscore);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (FontFormatException ex) {
                    ex.printStackTrace();
                }
                setVisible(false); // 창 안보이게 하기
            }
        });

        this.setSize(width, height);
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
    }

    class MyPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backGround, 0, 0,width, height, this);

        }
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new GameClear();
    }
}

