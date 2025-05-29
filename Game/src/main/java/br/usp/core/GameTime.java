/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.core;

/**
 *
 * @author Fernando
 */
public class GameTime {
    private long startTime;
    private long pausedTime;
    private long totalPausedDuration;
    private boolean paused;
    
    private long countdownMillis;
    
    public GameTime() {
        this(0);                                // Cria um jogo sem contagem regressiva!
    }

    public GameTime(long countdownMillis) {
        this.countdownMillis = countdownMillis;
        start();
    }

    public void start() {
        startTime = System.currentTimeMillis();
        pausedTime = 0;
        totalPausedDuration = 0;
        paused = false;
    }

    public void pause() {
        if (!paused) {
            paused = true;
            pausedTime = System.currentTimeMillis();
        }
    }

    public void resume() {
        if (paused) {
            totalPausedDuration += System.currentTimeMillis() - pausedTime;
            paused = false;
        }
    }

    public void reset() {
        start();
    }

    public long getElapsedTimeMillis() {
        if (paused) {
            return pausedTime - startTime - totalPausedDuration;
        }
        return System.currentTimeMillis() - startTime - totalPausedDuration;
    }

    public int getElapsedTimeSeconds() {
        return (int) (getElapsedTimeMillis() / 1000);
    }

    public long getRemainingTimeMillis() {
        if (countdownMillis == 0) return Long.MAX_VALUE;
        return Math.max(countdownMillis - getElapsedTimeMillis(), 0);
    }

    public int getRemainingTimeSeconds() {
        return (int) (getRemainingTimeMillis() / 1000);
    }

    public boolean isTimeUp() {
        return countdownMillis > 0 && getRemainingTimeMillis() <= 0;
    }

    public void addTimeMillis(long millis) {
        countdownMillis += millis;
    }

    public void subtractTimeMillis(long millis) {
        countdownMillis = Math.max(countdownMillis - millis, 0);
    }

    public boolean isPaused() {
        return paused;
    }
}
