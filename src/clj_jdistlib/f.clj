(in-ns 'clj-jdistlib.dist)

(import '(net.sourceforge.jdistlib F))

;(require '[clj-jdistlib.common :as cljc])

(defn rf
  "generate sample from F distribution"
  ([df1 df2] (F/random df1 df2 cljc/MT))
  ([n df1 df2]
   (-> (F/random n df1 df2 cljc/MT)
       (seq))))

(defn df
  "density(double x, double m, double n, boolean give_log)"
  [x df1 df2 give-log]
  (-> [(F/density x df1 df2 give-log)]
      (seq)))

(defn cf
  "cumulative(double x, double df1, double df2, boolean lower_tail, boolean log_p)"
  [x df1 df2 lower-tail log-p]
  (-> [(F/cumulative x df1 df2 lower-tail log-p)]
      (seq)))

(defn qf
  "quantile(double p, double df1, double df2, boolean lower_tail, boolean log_p)"
  [p df1 df2 lower-tail log-p]
  (-> [(F/quantile p df1 df2 lower-tail log-p)]
      (seq)))
