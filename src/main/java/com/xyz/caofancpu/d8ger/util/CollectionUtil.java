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
 * 集合工具类: https://dzone.com/articles/functional-programming-patterns-with-java-8
 */
public class CollectionUtil extends CollectionUtils {

    /**
     * 求元素类型相同的两个集合的并集(a ∪ b), 可指定结果容器类型
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
     * 求元素类型相同的两个集合的交集(a ∩ b), 可指定结果容器类型
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
     * 求元素类型相同的两个集合的交集的补集((a ∪ b) - (a ∩ b)), 可指定结果容器类型
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
     * 求元素类型相同的两个集合的差集(a - ( a ∩ b)), 可指定结果容器类型
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
     * 对列表元素指定函数(字段为数字类型)求和
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
     * 对列表元素指定函数(字段为数字类型)求平均
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
     * 对列表元素指定函数(字段为数字类型), 求最大值
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
     * 对列表元素指定函数(字段为数字类型), 求最小值
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
     * 对集合元素指定字段检测重复, 返回非空重复元素
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
     * 将按照分隔符固定拼接的[数字]字符串转换为指定[数字]类型的List
     *
     * @param source
     * @param splitSymbol
     * @param numberTypeMapper
     * @param <T>
     * @return
     */
    public static <T extends Number> List<T> splitDelimitedStringToList(@NonNull String source, @NonNull String splitSymbol, Function<String, T> numberTypeMapper) {
        return transToList(Arrays.asList(source.split(splitSymbol)), numberTypeMapper);
    }

    /**
     * Map判空
     *
     * @param sourceMap 数据源
     * @return boolean 判断结果
     */
    public static boolean isEmpty(Map sourceMap) {
        return Objects.isNull(sourceMap) || sourceMap.isEmpty();
    }

    public static boolean isNotEmpty(Map sourceMap) {
        return !isEmpty(sourceMap);
    }

    /**
     * 转换为Set, 底层默认使用HashSet
     *
     * @param source 数据源
     * @param mapper 字段执行函数
     * @return HashSet
     */
    public static <E, R> Set<R> transToSet(Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toSet());
    }

    /**
     * 转换为List, 底层默认使用ArrayList
     *
     * @param source 数据源
     * @param mapper 字段执行函数
     * @return ArrayList
     */
    public static <E, R> List<R> transToList(Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * 转换为指定的集合，常用Set/List，HashSet/ArrayList，LinkedSet/LinkedList
     *
     * @param resultColl 指定集合容器
     * @param source     数据源
     * @param mapper     字段执行函数
     * @return C
     */
    public static <E, R, C extends Collection<R>> C transToCollection(Supplier<C> resultColl, Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toCollection(resultColl));
    }

    /**
     * 两层嵌套Collection折叠平铺为List, 底层默认使用ArrayList
     * 多层嵌套的可以通过重复调用此方法完成平铺
     *
     * @param source 两层嵌套List数据源
     * @param mapper 外层元素获取内Collection的执行函数
     * @return 平铺后收集到的List
     */
    public static <E, R> List<R> transToListWithFlatMap(Collection<E> source, Function<? super E, ? extends List<R>> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * 两层嵌套Collection折叠平铺为Set去重, 底层默认使用HashSet
     * 多层嵌套的可以通过重复调用此方法完成平铺
     *
     * @param source 两层嵌套List数据源
     * @param mapper 外层元素获取内Collection的执行函数
     * @return 平铺后收集到的List
     */
    public static <E, R> Set<R> transToSetWithFlatMap(Collection<E> source, Function<? super E, ? extends List<R>> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * 根据元素字段满足一定条件执行过滤, 并转换为Set
     *
     * @param coll      原始数据源
     * @param predicate 筛选条件
     * @param mapper    对筛选出元素进行计算的函数
     * @return HashSet
     */
    public static <F, T> Set<F> filterAndTransSet(Collection<T> coll, Predicate<? super T> predicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(predicate).map(mapper).collect(Collectors.toSet());
    }

    /**
     * 踢除满足条件removePredicate的元素字段 并转换为Set
     * 本方法与{@link #filterAndTransSet}筛选逻辑是相反的, 结果是互补的
     *
     * @param coll            原始数据源
     * @param removePredicate 筛选条件
     * @param mapper          对筛选出元素进行计算的函数
     * @return HashSet
     */
    public static <F, T> Set<F> removeAndTransSet(Collection<T> coll, Predicate<? super T> removePredicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(item -> !removePredicate.test(item)).map(mapper).collect(Collectors.toSet());
    }

    /**
     * 根据元素字段满足一定条件执行过滤, 并转换为List
     *
     * @param coll      原始数据源
     * @param predicate 筛选条件
     * @param mapper    对筛选出元素进行计算的函数
     * @return ArrayList
     */
    public static <F, T> List<F> filterAndTransList(Collection<T> coll, Predicate<? super T> predicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(predicate).map(mapper).collect(Collectors.toList());
    }

    /**
     * 踢除满足条件removePredicate的元素字段 并转换为List
     * 本方法与{@link #filterAndTransList}筛选逻辑是相反的, 结果是互补的
     *
     * @param coll            原始数据源
     * @param removePredicate 筛选条件
     * @param mapper          对筛选出元素进行计算的函数
     * @return ArrayList
     */
    public static <F, T> List<F> removeAndTransList(Collection<T> coll, Predicate<? super T> removePredicate, Function<? super T, ? extends F> mapper) {
        return coll.stream().filter(Objects::nonNull).filter(item -> !removePredicate.test(item)).map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据元素字段满足一定条件执行过滤, 并转换为指定集合
     *
     * @param resultColl       结果收集容器
     * @param sourceColl       原始数据源
     * @param survivePredicate 保留条件
     * @param mapper           对筛选出元素进行计算的函数
     * @return R
     */
    public static <T, F, R extends Collection<F>> R filterAndTransColl(Supplier<R> resultColl, Collection<T> sourceColl, Predicate<? super T> survivePredicate, Function<? super T, ? extends F> mapper) {
        return sourceColl.stream().filter(Objects::nonNull).filter(survivePredicate).map(mapper).collect(Collectors.toCollection(resultColl));
    }

    /**
     * 踢除满足条件removePredicate的元素字段 并转换为List
     * 本方法与{@link #filterAndTransColl}筛选逻辑是相反的, 结果是互补的
     *
     * @param resultColl      结果收集容器
     * @param sourceColl      原始数据源
     * @param removePredicate 剔除条件
     * @param mapper          对筛选出元素进行计算的函数
     * @return R
     */
    public static <T, F, R extends Collection<F>> R removeAndTransColl(Supplier<R> resultColl, Collection<T> sourceColl, Predicate<? super T> removePredicate, Function<? super T, ? extends F> mapper) {
        return sourceColl.stream().filter(Objects::nonNull).filter(item -> !removePredicate.test(item)).map(mapper).collect(Collectors.toCollection(resultColl));
    }


    /**
     * 获取元素的某个字段集合, 并去重
     *
     * @param source 数据源
     * @param mapper 字段执行函数
     * @return
     */
    public static <E, R> List<R> distinctList(Collection<E> source, Function<? super E, ? extends R> mapper) {
        return source.stream().filter(Objects::nonNull).map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 根据集合元素中指定字段进行去重，返回去重后的元素集合
     *
     * @param coll               数据源
     * @param distinctComparator 元素字段比较器(可以是多个字段的联合比较器)
     * @return 去重后的原始元素集合
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
     * 为避免数据丢失，Steam API底层对Collectors.toMap做了较为硬性的要求
     * 1.toMap首先不允许key重复， 因而分组时需要注意使用KEY字段
     * 2.value不允许为null
     *
     * 因而，以下*ToMap方法在使用时请注意以上两条，而*ToMapEnhance允许key重复，并启用新值替换旧值的机制
     *
     */

    /**
     * 分组转换为Map<K, List<V>>，底层默认HashMap<K, ArrayList<V>>
     *
     * @param source
     * @param kFunction
     * @return
     */
    public static <E, K> Map<K, List<E>> groupIndexToMap(Collection<E> source, Function<? super E, ? extends K> kFunction) {
        return source.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(kFunction));
    }

    /**
     * 分组转换为Map<K, List<V>>, 支持key函数, value函数, 底层默认HashMap<K, ArrayList<V>>
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
     * 分组转换为指定的Map<K, List<V>>， 例如TreeMap<K, List<V>>/LinkedHashMap<K, List<V>>
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
     * 分组转换为指定Map<K, 指定的List<V>>，例如TreeMap<K, LinkedList<V>>/LinkedHashMap<K, LinkedList<V>>
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
     * 分组转换为指定Map<K, 指定的List<V>>，例如TreeMap<K, LinkedList<V>>/LinkedHashMap<K, LinkedList<V>>
     * 并且可对原始数组元素进行计算(转化)为其他对象
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
     * 转换为Map-Value
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
     * 转换为Map-Value, 重复KEY将抛出异常
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
     * 转换为Map-Value, 允许重复KEY
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
     * 转换为Map-Value
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
     * 转换为Map-Value, 重复KEY将抛出异常
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
     * 可以将两层嵌套List，转换为Map<K, List<V>>，按照K叠加List<V>
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
     * 按照指定分隔符将数组元素拼接为字符串
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
     * 按照指定分隔符将数组元素拼接为字符串
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
     * 对Map排序
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
     * 在List中根据指定字段(函数)查找元素，找到任意一个就返回，找不到就返回null
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
     * 在List中根据自定字段(函数)查找元素，返回找到的第一个元素，找不到就返回null
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
     * 根据指定条件查找元素，返回找到的第一个元素，找不到就返回null
     *
     * @param coll
     * @param predicate
     * @return
     */
    public static <T> T findFirst(Collection<T> coll, Predicate<? super T> predicate) {
        return coll.stream().filter(predicate).findFirst().orElse(null);
    }

    /**
     * 从list里根据唯一字段值 查找所有满足条件不为Null的元素
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
     * 判断元素在list中是否存在
     *
     * @param coll
     * @param predicate
     * @return
     */
    public static <T> List<T> findAll(Collection<T> coll, Predicate<? super T> predicate) {
        return coll.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 判断元素在list中存在至少一个值，存在就立马返回
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
     * 判断元素在list中是否存在
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
     * 判断元素在list中是否存在
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
     * Map键值对反转
     * 示例，
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
     * Map键值对反转，支持返回结果自定义收集容器
     * 例如返回LinkedHashMap<K, LinkedList<V>
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
     * 针对复杂Map中，查找key匹配函数的键值对集合
     * 不满足匹配函数条件时返回空
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
     * 针对复杂Map中，查找key匹配函数的键值对，只取一个
     * 不满足匹配函数条件时返回null
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
     * 针对复杂Map中，查找key匹配函数的键值对，只取一个
     * 不满足匹配函数条件时返回null
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
            throw new IllegalStateException(String.format("转换Map时不允许重复Key: [%s]", u));
        };
    }

    private static <T> BinaryOperator<T> enableNewOnDuplicateKey() {
        return (oldValue, newValue) -> newValue;
    }
}
