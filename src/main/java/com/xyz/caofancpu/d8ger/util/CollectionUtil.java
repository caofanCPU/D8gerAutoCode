package com.xyz.caofancpu.d8ger.util;


import com.google.common.collect.Lists;
import lombok.NonNull;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Collection tools: https://dzone.com/articles/functional-programming-patterns-with-java-8
 */
public class CollectionUtil extends CollectionUtils {

    /**
     * Find the union of two sets of the same element type, like (a ∪ b),
     * the container type of the result can be specified
     *
     * @param resultColl
     * @param a
     * @param b
     * @param <E>
     * @param <C>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E, C extends Collection<E>> C union(Supplier<C> resultColl, Collection<E> a, Collection<E> b) {
        C result = resultColl.get();
        union(a, b).forEach(item -> result.add((E) item));
        return result;
    }

    /**
     * Find the intersection of two sets of the same element type, like (a ∩ b),
     * the container type of the result can be specified
     *
     * @param resultColl
     * @param a
     * @param b
     * @param <E>
     * @param <C>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E, C extends Collection<E>> C intersection(Supplier<C> resultColl, Collection<E> a, Collection<E> b) {
        C result = resultColl.get();
        intersection(a, b).forEach(item -> result.add((E) item));
        return result;
    }

    /**
     * Find the complement of the intersection of two sets of the same element type, like ((a ∪ b) - (a ∩ b)),
     * the container type of the result can be specified
     *
     * @param resultColl
     * @param a
     * @param b
     * @param <E>
     * @param <C>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E, C extends Collection<E>> C disjunction(Supplier<C> resultColl, Collection<E> a, Collection<E> b) {
        C result = resultColl.get();
        disjunction(a, b).forEach(item -> result.add((E) item));
        return result;
    }

    /**
     * Find the difference between two sets of the same element type, like (a - ( a ∩ b)),
     * the container type of the result can be specified
     *
     * @param resultColl
     * @param a
     * @param b
     * @param <E>
     * @param <C>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E, C extends Collection<E>> C subtract(Supplier<C> resultColl, Collection<E> a, Collection<E> b) {
        C result = resultColl.get();
        subtract(a, b).forEach(item -> result.add((E) item));
        return result;
    }

    /**
     * Sum the specified elements of a list element (fields are numeric)
     *
     * @param coll
     * @param numberValueFunction
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F extends Number, T> BigDecimal sum(Collection<T> coll, Function<? super T, ? extends F> numberValueFunction) {
        double sum = coll.stream()
                .filter(Objects::nonNull)
                .map(numberValueFunction)
                .mapToDouble(Number::doubleValue)
                .sum();
        return BigDecimal.valueOf(sum);
    }

    /**
     * Average the specified elements of a list element (fields are numeric)
     *
     * @param coll
     * @param numberValueFunction
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F extends Number, T> BigDecimal average(Collection<T> coll, Function<? super T, ? extends F> numberValueFunction) {
        Double average = coll.stream()
                .filter(Objects::nonNull)
                .map(numberValueFunction)
                .collect(Collectors.averagingDouble(Number::doubleValue));
        return BigDecimal.valueOf(average);
    }

    /**
     * Max the specified elements of a list element (fields are numeric)
     *
     * @param coll
     * @param numberValueFunction
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F extends Number, T> BigDecimal max(Collection<T> coll, Function<? super T, ? extends F> numberValueFunction) {
        double max = coll.stream()
                .filter(Objects::nonNull)
                .map(numberValueFunction)
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(0d);
        return BigDecimal.valueOf(max);
    }

    /**
     * Min the specified elements of a list element (fields are numeric)
     *
     * @param coll
     * @param numberValueFunction
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F extends Number, T> BigDecimal min(Collection<T> coll, Function<? super T, ? extends F> numberValueFunction) {
        double min = coll.stream()
                .filter(Objects::nonNull)
                .map(numberValueFunction)
                .mapToDouble(Number::doubleValue)
                .min()
                .orElse(0d);
        return BigDecimal.valueOf(min);
    }

    /**
     * Detect duplicates for the specified field of the collection element,
     * return non-empty duplicate elements
     *
     * @param coll
     * @param mapper
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> Set<F> probeRepeatValueSet(Collection<T> coll, Function<? super T, F> mapper) {
        List<F> elementList = transToList(coll, mapper);
        List<F> withoutNullElementList = transToList(elementList, Function.identity());
        Set<F> noRepeatElementSet = new HashSet<>(withoutNullElementList);
        return subtract(HashSet::new, withoutNullElementList, noRepeatElementSet);
    }

    /**
     * Converts a [number] string that is concatenated according to the separator into a list of the specified [number]
     *
     * @param source
     * @param splitSymbol
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> List<T> splitDelimitedStringToList(@NonNull String source, @NonNull String splitSymbol, Function<String, T> mapper) {
        return transToList(Arrays.asList(source.split(splitSymbol)), mapper);
    }

    /**
     * Judge empty or not for Map
     *
     * @param sourceMap data source
     * @return boolean  judge result
     */
    public static boolean isEmpty(Map sourceMap) {
        return Objects.isNull(sourceMap) || sourceMap.isEmpty();
    }

    public static boolean isNotEmpty(Map sourceMap) {
        return !isEmpty(sourceMap);
    }

    /**
     * Convert to Set, HashSet is used by default
     *
     * @param source data source
     * @param mapper field execution function
     * @return HashSet
     */
    public static <E, R> Set<R> transToSet(Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toSet());
    }

    /**
     * Convert to List, ArrayList is used by default
     *
     * @param source data source
     * @param mapper field execution function
     * @return ArrayList
     */
    public static <E, R> List<R> transToList(Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * Convert to the specified collection, commonly used Set/List，HashSet/ArrayList，LinkedSet/LinkedList
     *
     * @param resultColl Specify a collection container
     * @param source     data source
     * @param mapper     field execution function
     * @return C
     */
    public static <E, R, C extends Collection<R>> C transToCollection(Supplier<C> resultColl, Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toCollection(resultColl));
    }

    /**
     * Two layers of Nested Collection are collapsed and tiled into a List, and ArrayList is used by default
     * Multi-level nesting can be completed by repeatedly calling this method
     *
     * @param source Two levels of nested List data sources
     * @param mapper Outer element gets execution function of inner collection
     * @return List collected after tiling
     */
    public static <E, R> List<R> transToListWithFlatMap(Collection<E> source, Function<? super E, ? extends List<R>> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * Two layers of Nested Collection are collapsed and tiled into a List, and HashSet is used by default
     * Multi-level nesting can be completed by repeatedly calling this method
     *
     * @param source Two levels of nested Set data sources
     * @param mapper Outer element gets execution function of inner collection
     * @return Set collected after tiling
     */
    public static <E, R> Set<R> transToSetWithFlatMap(Collection<E> source, Function<? super E, ? extends List<R>> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * Perform filtering based on element fields meeting certain conditions, and convert to Set
     *
     * @param coll      data source
     * @param predicate filter conditions
     * @param mapper    functions that compute the filtered out elements
     * @return HashSet
     */
    public static <F, T> Set<F> filterAndTransSet(Collection<T> coll, Predicate<? super T> predicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(predicate).map(mapper).collect(Collectors.toSet());
    }

    /**
     * Remove the element fields that meet the condition removePredicate and convert to Set.
     * This method is opposite with {@link #filterAndTransSet}, so their results are complementary
     *
     * @param coll            data source
     * @param removePredicate filter conditions
     * @param mapper          functions that compute the filtered out elements
     * @return HashSet
     */
    public static <F, T> Set<F> removeAndTransSet(Collection<T> coll, Predicate<? super T> removePredicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(item -> !removePredicate.test(item)).map(mapper).collect(Collectors.toSet());
    }

    /**
     * Perform filtering based on element fields meeting certain conditions, and convert to List.
     *
     * @param coll      data source
     * @param predicate filter conditions
     * @param mapper    functions that compute the filtered out elements
     * @return ArrayList
     */
    public static <F, T> List<F> filterAndTransList(Collection<T> coll, Predicate<? super T> predicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(predicate).map(mapper).collect(Collectors.toList());
    }

    /**
     * Perform filtering based on the element field meeting certain conditions, and convert to List.
     * This method is opposite with {@link #filterAndTransList}, so their results are complementary
     *
     * @param coll            data source
     * @param removePredicate filter conditions
     * @param mapper          functions that compute the filtered out elements
     * @return ArrayList
     */
    public static <F, T> List<F> removeAndTransList(Collection<T> coll, Predicate<? super T> removePredicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(item -> !removePredicate.test(item)).map(mapper).collect(Collectors.toList());
    }

    /**
     * Perform filtering based on the element field meeting certain conditions,
     * and convert to the specified collection
     *
     * @param resultColl       results collection container
     * @param sourceColl       data source
     * @param survivePredicate retention conditions
     * @param mapper           functions that compute the filtered out elements
     * @return R
     */
    public static <T, F, R extends Collection<F>> R filterAndTransColl(Supplier<R> resultColl, Collection<T> sourceColl, Predicate<? super T> survivePredicate, Function<? super T, ? extends F> mapper) {
        return sourceColl.stream().filter(Objects::nonNull).filter(survivePredicate).map(mapper).collect(Collectors.toCollection(resultColl));
    }

    /**
     * Remove the element fields that meet the condition removePredicate and convert to List.
     * This method is opposite with {@link #filterAndTransColl}, so their results are complementary
     *
     * @param resultColl      results collection container
     * @param sourceColl      data source
     * @param removePredicate elimination conditions
     * @param mapper          functions that compute the filtered out elements
     * @return R
     */
    public static <T, F, R extends Collection<F>> R removeAndTransColl(Supplier<R> resultColl, Collection<T> sourceColl, Predicate<? super T> removePredicate, Function<? super T, ? extends F> mapper) {
        return sourceColl.stream().filter(Objects::nonNull).filter(item -> !removePredicate.test(item)).map(mapper).collect(Collectors.toCollection(resultColl));
    }


    /**
     * Get a set of fields of an element, and remove duplicates
     *
     * @param source data source
     * @param mapper field execution function
     * @return
     */
    public static <E, R> List<R> distinctList(Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * Deduplication according to the specified field in the collection element,
     * return the deduplicated element collection
     *
     * @param coll               data source
     * @param distinctComparator Element field comparator (may be a joint comparator for multiple fields)
     * @return Deduplicated original element collection
     */
    public static <T> List<T> distinctListByField(Collection<T> coll, Comparator<T> distinctComparator) {
        if (isEmpty(coll)) {
            return Collections.emptyList();
        }
        return coll.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(distinctComparator)), ArrayList::new));
    }

    /**
     * In order to avoid data loss, the bottom layer of the Steam API makes more rigid requirements for Collectors.toMap
     * 1.methods named 'toMap' are not allow duplicate keys first,
     * so you need to pay attention to using the KEY field when grouping
     * 2.not allow null VALUE either
     *
     * Therefore, please pay attention to the above two when using the following named '*ToMap' method,
     * and named '*ToMapEnhance' method allows the key to be duplicated and enables the new value to replace the old value
     *
     */

    /**
     * Group conversion to Map<K, List <V>>, support key function, value function, HashMap<K, ArrayList<V>> is used by default
     *
     * @param source
     * @param kFunction
     * @return
     */
    public static <E, K> Map<K, List<E>> groupIndexToMap(Collection<E> source, Function<? super E, ? extends K> kFunction) {
        return source.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(kFunction));
    }

    /**
     * Group conversion to Map<K, List <V>>, support key function, value function, HashMap<K, ArrayList<V>> is used by default
     *
     * @param source
     * @param kFunction
     * @param vFunction
     * @param <E>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <E, K, V> Map<K, List<V>> groupIndexToMap(Collection<E> source, Function<? super E, ? extends K> kFunction, Function<? super E, ? extends V> vFunction) {
        return source.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(kFunction, HashMap::new, Collectors.mapping(vFunction, Collectors.toList())));
    }

    /**
     * Group conversion to specified Map<K, specified List<V>>, such as TreeMap<K, LinkedList<V>> | LinkedHashMap<K, LinkedList<V>>
     *
     * @param mapColl
     * @param source
     * @param kFunction
     * @return
     */
    public static <E, K, M extends Map<K, List<E>>> M groupIndexToMap(Supplier<M> mapColl, Collection<E> source, Function<? super E, ? extends K> kFunction) {
        return source.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(kFunction, mapColl, Collectors.toList()));
    }

    /**
     * Group conversion to specified Map<K, specified List<V>>, such as TreeMap<K, LinkedList<V>> | LinkedHashMap<K, LinkedList<V>>
     *
     * @param mapColl
     * @param vColl
     * @param source
     * @param kFunction
     */
    public static <E, K, M extends Map<K, C>, C extends Collection<E>> M groupIndexToMap(Supplier<M> mapColl, Supplier<C> vColl, Collection<E> source, Function<? super E, ? extends K> kFunction) {
        return source.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(kFunction, mapColl, Collectors.toCollection(vColl)));
    }

    /**
     * Group conversion to specified Map<K, specified List<V>>, such as TreeMap<K, LinkedList<V>> | LinkedHashMap<K, LinkedList<V>>,
     * and can calculate (convert) the original array elements into other objects
     *
     * @param mapColl
     * @param vColl
     * @param source
     * @param kGroupFunction
     * @param vFunction
     * @return
     */
    public static <E, K, V, M extends Map<K, C>, C extends Collection<V>> M groupIndexToMap(Supplier<M> mapColl, Supplier<C> vColl, Collection<E> source, Function<? super E, ? extends K> kGroupFunction, Function<? super E, ? extends V> vFunction) {
        return source.stream().filter(Objects::nonNull).collect(
                Collectors.groupingBy(kGroupFunction, mapColl, Collectors.mapping(vFunction, Collectors.toCollection(vColl))));
    }

    /**
     * Convert to Map<Key, Value>
     *
     * @param values
     * @param kFunction
     * @return
     */
    public static <E, K> Map<K, E> transToMap(@NonNull Iterable<E> values, Function<? super E, ? extends K> kFunction) {
        return StreamSupport.stream(values.spliterator(), Boolean.FALSE)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(kFunction, Function.identity()));
    }

    /**
     * Convert to Map-Value, any duplicate key will throw exception
     *
     * @param mapColl
     * @param values
     * @param kFunction
     * @return
     */
    public static <E, K, M extends Map<K, E>> M transToMap(Supplier<M> mapColl, @NonNull Iterable<E> values, Function<? super E, ? extends K> kFunction) {
        return StreamSupport.stream(values.spliterator(), Boolean.FALSE)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(kFunction, Function.identity(), nonDuplicateKey(), mapColl));
    }


    /**
     * Convert to Map<Key, Value>, allow duplicate key
     *
     * @param mapColl
     * @param values
     * @param kFunction
     * @return
     */
    public static <E, K, M extends Map<K, E>> M transToMapEnhance(Supplier<M> mapColl, @NonNull Iterable<E> values, Function<? super E, ? extends K> kFunction) {
        return StreamSupport.stream(values.spliterator(), Boolean.FALSE)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(kFunction, Function.identity(), enableNewOnDuplicateKey(), mapColl));
    }


    /**
     * Convert to Map<Key, Value>
     *
     * @param values
     * @param kFunction
     * @param vFunction
     * @return
     */
    public static <E, K, V> Map<K, V> transToMap(@NonNull Iterable<E> values, Function<? super E, ? extends K> kFunction, Function<? super E, ? extends V> vFunction) {
        return StreamSupport.stream(values.spliterator(), Boolean.FALSE)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(kFunction, vFunction));
    }


    /**
     * Convert to Map<Key, Value>, any duplicate key will throw exception
     *
     * @param mapColl
     * @param values
     * @param kFunction
     * @param vFunction
     * @return
     */
    public static <E, K, V, M extends Map<K, V>> M transToMap(Supplier<M> mapColl, @NonNull Iterable<E> values, Function<? super E, ? extends K> kFunction, Function<? super E, ? extends V> vFunction) {
        return StreamSupport.stream(values.spliterator(), Boolean.FALSE)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(kFunction, vFunction, nonDuplicateKey(), mapColl));
    }

    /**
     * Convert two nested Lists into Map<K, List<V>>, and superimpose List<V> according to K
     *
     * @param mapColl
     * @param values
     * @param kFunction
     * @param vFunction
     * @return
     */
    public static <E, K, V, M extends Map<K, List<V>>> M transToMapByMerge(Supplier<M> mapColl, Iterable<E> values, Function<? super E, K> kFunction, Function<? super E, List<V>> vFunction) {
        if (Objects.isNull(values)) {
            return mapColl.get();
        }
        return StreamSupport.stream(values.spliterator(), Boolean.FALSE)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(kFunction, vFunction, (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                }, mapColl));

    }

    /**
     * Splicing array elements into strings according to the specified delimiter
     *
     * @param arr
     * @param separator
     * @return
     */
    public static String join(Object[] arr, String separator) {
        if (ArrayUtils.isEmpty(arr)) {
            return StringUtils.EMPTY;
        }
        return join(Arrays.asList(arr), separator);
    }

    /**
     * Splicing array elements into strings according to the specified delimiter
     *
     * @param coll
     * @param separator
     * @return
     */
    public static <T> String join(Collection<T> coll, String separator) {
        if (CollectionUtil.isEmpty(coll)) {
            return StringUtils.EMPTY;
        }
        if (Objects.isNull(separator)) {
            separator = StringUtils.EMPTY;
        }
        return coll.stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(separator));
    }

    /**
     * Sorting Map
     *
     * @param sourceMap
     * @param comparator
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V extends Comparable<V>> LinkedHashMap<K, V> sortedMapByValue(Map<K, V> sourceMap, Comparator<? super Entry<K, V>> comparator) {
        if (isEmpty(sourceMap)) {
            return new LinkedHashMap<>(2, 0.5F, Boolean.FALSE);
        }
        List<Entry<K, V>> entryList = sourceMap.entrySet().stream().sorted(comparator).collect(Collectors.toList());
        return transToMap(LinkedHashMap::new, entryList, Entry::getKey, Entry::getValue);
    }

    /**
     * Find elements in the List according to the specified field function,
     * if any one is found, it will be returned, or null if it is not found.
     *
     * @param coll
     * @param function
     * @param value
     * @return
     */
    public static <T, F> T findAny(Collection<T> coll, Function<? super T, ? extends F> function, @NonNull F value) {
        return coll.stream().filter(item -> value.equals(function.apply(item))).findAny().orElse(null);
    }

    /**
     * Find a value in a array, normally used in Enum class
     *
     * @param source
     * @param function
     * @param value
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> T findAnyInArrays(T[] source, Function<? super T, ? extends F> function, @NonNull F value) {
        return findAny(Arrays.asList(source), function, value);
    }

    /**
     * Find elements in a list according to a custom field function,
     * return the first element found or null if not found
     *
     * @param coll
     * @param function
     * @param value
     * @return
     */
    public static <T, F> T findFirst(Collection<T> coll, Function<? super T, ? extends F> function, @NonNull F value) {
        return coll.stream().filter(item -> value.equals(function.apply(item))).findFirst().orElse(null);
    }

    /**
     * Finds elements according to specified conditions,
     * return the first element found or null if not found
     *
     * @param coll
     * @param predicate
     * @return
     */
    public static <T> T findFirst(Collection<T> coll, Predicate<? super T> predicate) {
        return coll.stream().filter(predicate).findFirst().orElse(null);
    }

    /**
     * Find all elements from the list based on unique field values
     *
     * @param coll
     * @param function
     * @param value
     * @return
     */
    public static <T, F> List<T> findAll(Collection<T> coll, Function<? super T, ? extends F> function, @NonNull F value) {
        if (isEmpty(coll)) {
            return Lists.newArrayList();
        }
        return coll.stream()
                .filter(Objects::nonNull)
                .filter(item -> value.equals(function.apply(item)))
                .collect(Collectors.toList());
    }

    /**
     * Determine and find all elements existed in the list
     *
     * @param coll
     * @param predicate
     * @return
     */
    public static <T> List<T> findAll(Collection<T> coll, Predicate<? super T> predicate) {
        return coll.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Determine if an element has at least one value in the list,
     * and return immediately if it exists
     *
     * @param coll
     * @param function
     * @param value
     * @return
     */
    public static <T, F> boolean existAtLeastOne(Collection<T> coll, Function<? super T, ? extends F> function, @NonNull F value) {
        return coll.stream().anyMatch(item -> value.equals(function.apply(item)));
    }

    /**
     * Determine if an element exists in the list
     *
     * @param coll
     * @param function
     * @param value
     * @return
     */
    public static <T, F> boolean exist(Collection<T> coll, Function<? super T, ? extends F> function, @NonNull F value) {
        return coll.stream().allMatch(item -> value.equals(function.apply(item)));
    }

    /**
     * Determine if an element exists in the list
     *
     * @param coll
     * @param predicate
     * @return
     */
    public static <T> boolean exist(Collection<T> coll, Predicate<? super T> predicate) {
        T existFirstOne = coll.stream().filter(predicate).findFirst().orElse(null);
        return Objects.nonNull(existFirstOne);
    }

    /**
     * Map key-value pair inversion,
     * for example,
     * { examA : [stu1, stu2, stu3], examB: [stu1, stu2] }
     * ⬇
     * {stu1 : [examA, examB], stu2 : [examA, examB], stu3 : [examA]}
     *
     * @param sourceMap
     * @param kFunction
     * @param vFunction
     * @return
     */
    public static <E1, E2, K1, K2> Map<K2, List<E2>> reverseKV(@NonNull Map<K1, List<E1>> sourceMap, Function<? super K1, ? extends E2> kFunction, Function<? super E1, ? extends K2> vFunction) {
        Map<K2, List<E2>> aux = new HashMap<>();
        sourceMap.entrySet().stream()
                .filter(Objects::nonNull)
                .forEach(entry -> entry.getValue().stream()
                        .filter(Objects::nonNull)
                        .forEach(v -> aux.computeIfAbsent(vFunction.apply(v), init -> new ArrayList<>()).add(kFunction.apply(entry.getKey())))
                );
        return aux;
    }

    /**
     * Map key-value pair inversion, support return a custom collection container,
     * for example, return LinkedHashMap<K, LinkedList<V>
     * <p>
     * Map<k1, Coll_1<v1>>  ==>  Map<k2, Coll_1<v2>>
     * kFunction.apply(k1) ==> v2
     * vFunction.apply(v1) ==> k2
     *
     * @param mapColl
     * @param vColl
     * @param sourceMap
     * @param kFunction
     * @param vFunction
     * @return
     */
    public static <V1, V2, K1, K2, C1 extends Collection<V1>, C2 extends Collection<V2>, M1 extends Map<K1, C1>, M2 extends Map<K2, C2>>
    M2 reverseKV(Supplier<M2> mapColl, Supplier<C2> vColl, @NonNull M1 sourceMap, Function<? super K1, ? extends V2> kFunction, Function<? super V1, ? extends K2> vFunction) {
        M2 aux = mapColl.get();
        sourceMap.entrySet().stream()
                .filter(Objects::nonNull)
                .forEach(entry -> entry.getValue().stream()
                        .filter(Objects::nonNull)
                        .forEach(v -> aux.computeIfAbsent(vFunction.apply(v), init -> vColl.get()).add(kFunction.apply(entry.getKey())))
                );
        return aux;
    }

    /**
     * For complex maps, find the set of key-value pairs for the key matching function
     * Returns null if the matching function conditions are not met
     *
     * @param srcMap
     * @param kFunction
     * @param value
     * @return
     */
    public static <K, V, T> List<Entry<K, V>> findInMap(Map<K, V> srcMap, Function<? super K, ? extends T> kFunction, @NonNull T value) {
        if (isEmpty(srcMap)) {
            return null;
        }
        return srcMap.entrySet().stream()
                .filter(Objects::nonNull)
                .map(entry -> {
                    if (value.equals(kFunction.apply(entry.getKey()))) {
                        return entry;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * For complex Maps, find the key-value pairs of the key matching function, and only take one
     * Returns null if the matching function conditions are not met
     *
     * @param srcMap
     * @param kFunction
     * @param value
     * @return
     */
    public static <K, V, T> Entry<K, V> findOneInMap(Map<K, V> srcMap, Function<? super K, ? extends T> kFunction, @NonNull T value) {
        if (isEmpty(srcMap)) {
            return null;
        }
        return srcMap.entrySet().stream()
                .filter(Objects::nonNull)
                .filter(entry -> value.equals(kFunction.apply(entry.getKey())))
                .findAny()
                .get();
    }

    /**
     * For complex Maps, find the key-value pairs of the key matching function, and only take one
     * Returns null if the matching function conditions are not met
     *
     * @param srcMap
     * @param kFunction
     * @param value
     * @return
     */
    public static <K, V, T> V findOneValue(Map<K, V> srcMap, Function<? super K, ? extends T> kFunction, @NonNull T value) {
        Entry<K, V> resultEntry = findOneInMap(srcMap, kFunction, value);
        return Objects.isNull(resultEntry) ? null : resultEntry.getValue();
    }

    /**
     * Returns a merge function, suitable for use in
     * {@link Map#merge(Object, Object, BiFunction) Map.merge()} or
     * throws {@code IllegalStateException}.  This can be used to enforce the
     * assumption that the elements being collected are distinct.
     *
     * @param <T> the type of input arguments to the merge function
     * @return a merge function which always throw {@code IllegalStateException}
     */
    private static <T> BinaryOperator<T> nonDuplicateKey() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate keys are not allowed when converting Map: [%s]", u));
        };
    }

    private static <T> BinaryOperator<T> enableNewOnDuplicateKey() {
        return (oldValue, newValue) -> newValue;
    }
}
