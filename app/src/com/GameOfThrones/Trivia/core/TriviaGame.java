package com.GameOfThrones.Trivia.core;

import java.util.ArrayList;

/**
 * Trivia game.
 * 
 * @author andre
 * 
 */
public class TriviaGame implements java.io.Serializable {
	/** Gives the user 21 seconds to answer each trivia */
	public final static long QUESTION_TIME = 21000;

	/**
	 * Score the user accumulates
	 */
	private int gameScore;

	/** Keep track of current game statsView */
	private int amountCorrect, questionsNumber;

	private static final long serialVersionUID = -408390483770836593L;

	private int triviaTime;

	private QuestionCollection allQuestions;

	private int numberOfQuestions;

	/**
	 * Constructor
	 * 
	 * @param allquestions
	 * @param numberOfQuestions
	 * @param triviaTime
	 */
	public TriviaGame(QuestionCollection allquestions, int numberOfQuestions,
			int triviaTime) {
		this.allQuestions = allquestions;
		allQuestions.shuffle();
		triviaTime = this.triviaTime;
		amountCorrect = 0;
		questionsNumber = 0;
		this.numberOfQuestions = numberOfQuestions;
	}

	/**
	 * Constructor
	 * 
	 * @param allquestions
	 * @param numberOfQuestions
	 * @param triviaTime
	 */
	public TriviaGame(QuestionCollection allquestions, int numberOfQuestions,
			int triviaTime, GameCharacter gameCharacter) {
		this(allquestions, numberOfQuestions, triviaTime);
		allquestions
				.keepOnly(Session.getInstance().getMap().get(gameCharacter));
	}

	/**
	 * Moves forward the question used.
	 * 
	 * @throws OutOfQuestionsException
	 */
	public void nextQuestion() throws OutOfQuestionsException {
		questionsNumber++;
		if (allQuestions.isDone()) {
			throw new OutOfQuestionsException();
		} else {
			allQuestions.next();
		}
	}

	/**
	 * Keeps questions only whose ids match any in the keepIds
	 * QuestionCollection.
	 * 
	 * @param keepsIds
	 *            - list of questions ids
	 */
	public void keepOnly(ArrayList<Integer> keepsIds) {
		allQuestions.keepOnly(keepsIds);
	}

	/**
	 * @return the gameScore
	 */
	public int getGameScore() {
		return gameScore;
	}

	/**
	 * @return the amountCorrect
	 */
	public int getAmountCorrect() {
		return amountCorrect;
	}

	/**
	 * If the choice was correct, a correct toast is shown. If an incorrect
	 * button was selected, a DialogFragment is shown displaying the correct
	 * choice. Will try to determine if the user has answered the total amount
	 * of trivia trivia, if they have endGame() is called, otherwise mapText()
	 * is called and the game goes on to the next trivia
	 * 
	 * @param button
	 *            - int representing the user's trivia choice
	 * @throws OutOfQuestionsException
	 */
	public boolean choiceSelected(int button, long timeLeft) {
		boolean correct = getCorrectChoice() == button;
		if (correct) {
			gameScore += (timeLeft * 23);
		}
		return correct;
	}

	/**
	 * 
	 * @return returns the current correct choice
	 */
	public int getCorrectChoice() {
		return allQuestions.current().getCorrectAnswer();
	}

	/**
	 * 
	 * @return the amount of questions answered
	 */
	public int getQuestionsAnswered() {
		return questionsNumber;
	}

	/**
	 * 
	 * @return Whether the game is over
	 */
	public boolean isGameOver() {
		return questionsNumber >= numberOfQuestions;
	}

	/**
	 * @return Returns the current question
	 */
	public Question getCurrentQuestion() {
		return allQuestions.current();
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}
}