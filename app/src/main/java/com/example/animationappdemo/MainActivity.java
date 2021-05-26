package com.example.animationappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Scene show_chuot, show_banphim, show_laptop, show_tainghe, scene_show_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle= findViewById(R.id.tvTitle);
        EditText etUserName= findViewById(R.id.etUserName);
        EditText etPass=findViewById(R.id.etPass);
        Button btLogin=findViewById(R.id.btLogin);

        tvTitle.setY(tvTitle.getY()-200);
        etUserName.setY(etUserName.getY()-200);
        etPass.setY(etPass.getY()-200);
        btLogin.setY(btLogin.getY()-200);

        tvTitle.setAlpha(0);
        etUserName.setAlpha(0);
        etPass.setAlpha(0);
        btLogin.setAlpha(0);

        ObjectAnimator animation, animation1, animation2, animation3;
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 40f);
        animation = ObjectAnimator.ofPropertyValuesHolder(tvTitle, alpha, pvhY);
        animation.setDuration(5000);
        animation.start();
        animation1 = ObjectAnimator.ofPropertyValuesHolder(etUserName, alpha, pvhY);
        animation1.setDuration(5000);
        animation1.start();
        animation2 = ObjectAnimator.ofPropertyValuesHolder(etPass, alpha, pvhY);
        animation2.setDuration(5000);
        animation2.start();
        animation3 = ObjectAnimator.ofPropertyValuesHolder(btLogin, alpha, pvhY);
        animation3.setDuration(5000);
        animation3.start();


        ViewGroup rootScene=findViewById(R.id.sceneRoot);
        scene_show_product =Scene.getSceneForLayout(rootScene,R.layout.show_product, this);
        show_chuot=Scene.getSceneForLayout(rootScene,R.layout.show_chuot,this);
        show_banphim=Scene.getSceneForLayout(rootScene,R.layout.show_banphim,this);
        show_laptop=Scene.getSceneForLayout(rootScene,R.layout.show_laptop,this);
        show_tainghe=Scene.getSceneForLayout(rootScene,R.layout.show_tainghe,this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition Fade=new Fade();
                TransitionManager.go(scene_show_product,Fade);
            }
        });
    }
    public void Back(View view)
    {
        Transition Fade=new Fade();
        TransitionManager.go(scene_show_product,Fade);
    }
    public void chuotClick(View view)
    {
        Transition Fade=new Explode();
        TransitionManager.go(show_chuot,Fade);
    }
    public void banphimClick(View view)
    {
        Transition Fade=new Explode();
        TransitionManager.go(show_banphim,Fade);
    }
    public void laptopClick(View view)
    {
        Transition Fade=new Explode();
        TransitionManager.go(show_laptop,Fade);
    }
    public void taingheClick(View view)
    {
        Transition Fade=new Explode();
        TransitionManager.go(show_tainghe,Fade);
    }

    public void Buy(View view)
    {
        ImageView img=findViewById(R.id.image);
        ImageView cart=findViewById(R.id.cart);
        ObjectAnimator animator;
        ValueAnimator animation = ValueAnimator.ofFloat(0f ,1f);

        animation.setDuration(3000);
        float valX=-4*(img.getX()-cart.getX());
        float valY=-4*(img.getY()-cart.getY());
        Button btBuy=findViewById(R.id.buy);
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                        float animatedValue = (float)updatedAnimation.getAnimatedValue();
                        img.setTranslationX(animatedValue*valX);
                        img.setTranslationY(animatedValue*valY);
                        img.setScaleX(1-animatedValue);
                        img.setScaleY(1-animatedValue);
                    }
                });
                animation.start();
            }
        });
    }
}