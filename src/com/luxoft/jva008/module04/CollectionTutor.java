package com.luxoft.jva008.module04;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Implement method joinByCycle(). Check the result.
 * Implement methods getAnimalsList(), getAnimalsSet() and return its values.
 * How do the results differ?
 * Implement method joinByIterator()
 */

public class CollectionTutor {
	String [] animals =
        {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human", "Cow"};

   /**
   * This method should return the ArrayList with all the animals
   */
   public List<String> getAnimalsList() {
       log(String.join(",", Arrays.asList(animals)));
       return Arrays.asList(animals);
   }

   /**
   * The method must return a Set with all the animals
   */
   public Set<String> getAnimalsSet() {
       return null;
   }

   /**
	* Method should take a collection of strings
	* and return one string consisting of all collection elements,
	* separated by commas, using for cycle
   */
   public String joinByCycle(Collection<?> c) {
       return null;
   }

   /**
	* Method should take a collection of strings
	* and return one string consisting of all collection elements,
	* separated by commas, using iterator
   */
   public String joinByIterator(Collection<?> c) {
       return null;
   }

   @Test
   public void testCollections() {
       log("getAnimalsList: " + joinByCycle(Arrays.asList(animals)));

       log("getAnimalsList: " + joinByCycle(getAnimalsList()));
       log("getAnimalsSet: " + joinByCycle(getAnimalsSet()));

       log("getAnimalsList by iterator: " + joinByIterator(getAnimalsList()));
   }

   @Test
   public void getAnimalsListShouldReturnsListContainsAllAnimals() {
       List<String> list = getAnimalsList();
       assertTrue(list.containsAll(Arrays.asList(animals)));
   }

   @Test
   public void getAnimalsSetShouldReturnsSetContainsAllAnimals() {
       Set<String> set = getAnimalsSet();
       assertTrue(set.containsAll(Arrays.asList(animals)));
   }

   @Test
   public void joinByCycleShouldReturnsStringWithAllAnimalsJoined() {
       String result = joinByCycle(make123Collection());
       assertEquals("1,2,3", result);
   }

   @Test
   public void joinByIteratorShouldReturnsStringWithAllAnimalsJoined() {
       String result = joinByIterator(make123Collection());
       assertEquals("1,2,3", result);
   }

   private Collection<String> make123Collection() {
       Collection<String> collection = new LinkedHashSet<String>();
       collection.add("1");
       collection.add("2");
       collection.add("3");
       return collection;
   }
}
