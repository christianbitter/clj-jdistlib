(ns clj-jdistlib.common)

(def MT (new net.sourceforge.jdistlib.rng.MersenneTwister))

(defn sqr
  "Square a value"
  [a]
  (* a a))

(defn sqrt
  "Square Root of a value"
  [a]
  (Math/sqrt a))
