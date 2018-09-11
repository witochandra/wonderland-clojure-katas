(ns alphabet-cipher.coder
  (:require [clojure.string :as str]))

(defn- normalize
  [c]
  (- (int c) (int \a)))

(defn- denormalize
  [i]
  (char (+ 97 (mod i 26))))

(defn- my-repeat
  [i u]
  (->> (repeat (inc (/ (count u) (count i))) i)
       (apply str)))

(defn encode [keyword message]
  (->> (cycle keyword)
       (vector message)
       (apply map #(+ (normalize %1) (normalize %2)))
       (map denormalize)
       (apply str)))

(defn decode [keyword message]
  (->> (cycle keyword)
       (vector message)
       (apply map #(- (normalize %1) (normalize %2)))
       (map denormalize)
       (apply str)))

(defn decipher [cipher message]
  (let [possible-keys (->> [cipher message]
                           (apply map #(- (normalize %1) (normalize %2)))
                           (map denormalize)
                           (reduce #(conj %1 (str (last %1) %2)) []))
        last-key (last possible-keys)]
    (->> possible-keys
         (map #(hash-map :re (my-repeat % last-key) :key %))
         (filter #(.contains (:re %) last-key))
         (first)
         (:key))))
