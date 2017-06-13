(ns clj-jdistlib.stat)

(defn mean
  "mean of vector"
  [avec]
  (let [n (count avec)
        ninv (/ 1 n)
        s (->> avec
               (reduce +))]
    (* ninv s)))
