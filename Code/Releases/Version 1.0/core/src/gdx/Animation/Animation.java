package gdx.Animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import javafx.scene.layout.Background;

public class Animation extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img2, img3, imgWL, imgWR;
	Sprite sprite;
	TextureRegion[] animationFrames;
	com.badlogic.gdx.graphics.g2d.Animation animation;
	Float elapsedTime;

	int nScreen=1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Background.jpg");
		img2 = new Texture("0.png");
		img3 = new Texture("Range.jpg");
		imgWL = new Texture("Walkleft.png");
		imgWR = new Texture("Walkright.png");
		sprite = new Sprite(img2);
		sprite.setPosition(0, 200);
		TextureRegion[][] tmpFrames = TextureRegion.split(imgWR, 200, 200);
		animationFrames = new TextureRegion[4];
		int index = 0;
		for (int i = 0; i < 2; i++) {
				animationFrames[index++] = tmpFrames[i];
			}
			animation = new com.badlogic.gdx.graphics.g2d.Animation(1f/4f, animationFrames);
		}


	@Override
	public void render () {
		elapsedTime+= Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			sprite.translateX(-5f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			sprite.translateX(5f);
		}
		if(sprite.getX()>=img.getWidth()-sprite.getWidth()/2){
			nScreen =2;
			sprite.setPosition(0, 200);
		}
		if(sprite.getX()<=-1&&nScreen==2){
			nScreen =1;
			sprite.setPosition(img.getWidth()-sprite.getWidth()/2, 200);
		}
		else if(sprite.getX()<=-1){
			nScreen=3;
		}
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(nScreen ==1){
			batch.draw(img, 0, -95);
		}
		else if(nScreen ==2){
			batch.draw(img3, 0, 0);
		}
		batch.draw(img2, sprite.getX(), sprite.getY());
		batch.draw(animation.getKeyFrame(elapsedTime,true), 0, 0);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		img2.dispose();
	}
}
