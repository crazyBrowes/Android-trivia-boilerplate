package com.GameOfThrones.Trivia.core;

import java.util.ArrayList;

/**
 * Represents a chracter from the content the trivia app is built from
 * @author andre
 *
 */
public class GameCharacter {
	/**
	 * Character's name
	 */
	protected String name;
	/**
	 * Names to search under the character
	 */
	protected ArrayList<String> searchTerms;
	
	/**
	 * Constructor
	 * @param name - initialize instance variable
	 * @param aliases - init instance variable
	 */
	public GameCharacter(String name, ArrayList<String> aliases) {
		super();
		this.name = name;
		setSearchTerms(aliases);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the searchTerms
	 */
	public ArrayList<String> getSearchTerms() {
		return searchTerms;
	}

	/**
	 * @param searchTerms
	 *            the searchTerms to set
	 */
	public void setSearchTerms(ArrayList<String> searchTerms) {
		if (!searchTerms.contains(this.name)) {
			searchTerms.add(name);
		}
		this.searchTerms = searchTerms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameCharacter [name=" + name + ", searchTerms=" + searchTerms + "]";
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameCharacter other = (GameCharacter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
