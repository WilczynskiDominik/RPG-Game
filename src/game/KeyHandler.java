package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        settKeyStatus(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        settKeyStatus(e, false);
    }

    //method that set pressed key status
    private void settKeyStatus(KeyEvent e, boolean status){

        int code = e.getKeyCode();  //return number of the pressed key

        if(code == KeyEvent.VK_W){
            upPressed = status;
        }
        if(code == KeyEvent.VK_S){
            downPressed = status;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = status;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = status;
        }
    }


    public boolean isOnlyOneKeyPressed(){
        if(isKeyPressed()){
            if(isUpPressed() && (isDownPressed() || isLeftPressed() || isRightPressed())){
                return false;
            }
            if(isDownPressed() && (isUpPressed() || isLeftPressed() || isRightPressed())){
                return false;
            }
            if(isLeftPressed() && (isDownPressed() || isUpPressed() || isRightPressed())){
                return false;
            }
            if(isRightPressed() && (isDownPressed() || isLeftPressed() || isUpPressed())){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean isKeyPressed(){
        return isUpPressed() || isDownPressed() || isLeftPressed() || isRightPressed();
    }
    //GETTERS
    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
}
