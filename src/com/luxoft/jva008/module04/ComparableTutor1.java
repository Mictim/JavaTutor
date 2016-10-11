package com.luxoft.jva008.module04;

import static org.junit.Assert.assertEquals;
import static com.luxoft.jva008.Logger.log;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 *	Implement method Set<Animal> getAnimalsOrderedByNameSet()
 * 	and method Set<Animal> getAnimalsOrderedByNameSetDesc()
 */

public class ComparableTutor1 {
	String [] animals =
        {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    class Animal implements Comparable<Animal> {
        String name;
        public Animal(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }
        @Override
        public int compareTo(Animal other) {
            if (name == null) {
                return -1;
            }
            return name.compareTo(((Animal)other).name);
        }
    }

    /**
	 * Method should return a Set of Animal instances, alphabetically sorted
	 * Use TreeSet for that and implement Comparable interface in Animal class.
     */
    public Set<Animal> getAnimalsOrderedByNameSet() {
        return new TreeSet<Animal>(toAnimal());
    }

    /**
	 * Method should return a Set of Animal instances,
	 * ordered by the name in reverse order.
	 * Use TreeSet for that and pass a Comparator implementation as a parameter.
     *
     */
    public Set<Animal> getAnimalsOrderedByNameSetDesc() {
        TreeSet<Animal> treeSet = new TreeSet<Animal>(new Comparator<Animal>() {
        	@Override
			public int compare(Animal a1, Animal a2) {
			    if (a1.name == null) {
                    return 1;
			    }
				return -a1.name.compareTo(a2.name);
			}
        });
        treeSet.addAll(toAnimal());
        return treeSet;
    }

    private List<Animal> toAnimal() {
        return Arrays.asList(animals).stream().map(a->new Animal(a)).collect(Collectors.toList());
    }
    
    public String joinByCycle(Collection<?> c) {
        if (c == null) {
        	return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Object s: c) {
            builder.append(s);
            if (builder.length()>0) builder.append(", ");
        }
        return builder.toString();
    }

    @Test
    public void testCollections() {
        log("getAnimalsList: " + joinByCycle(Arrays.asList(animals)));

        log("getAnimalsOrderedByNameSet: " + joinByCycle(getAnimalsOrderedByNameSet()));
        log("getAnimalsOrderedByNameSetDesc: " + joinByCycle(getAnimalsOrderedByNameSetDesc()));
    }

    @Test
    public void getAnimalsOrderedByNameSet_default_returnsSortedSet() {
        Set<Animal> set = getAnimalsOrderedByNameSet();
        String join = joinByCycle(set);
        assertEquals("Cat, Chicken, Cow, Dog, Elephant, Goose, Horse, Human, Rabbit, Snake, ", join);
    }

    @Test
    public void getAnimalsOrderedByNameSetDesc_default_returnsSortedSet() {
        Set<Animal> set = getAnimalsOrderedByNameSetDesc();
        String join = joinByCycle(set);
        assertEquals("Snake, Rabbit, Human, Horse, Goose, Elephant, Dog, Cow, Chicken, Cat, ", join);
    }

}
