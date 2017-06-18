(in-ns 'clj-jdistlib.dist)

(import
 '(net.sourceforge.jdistlib Normal))
 
(def NORMAL (new Normal))

(defn rnorm
  "constructs a sequence of random normal variables"
  ([] (seq [(. NORMAL random)]))
  ([mu sigma] (seq [(Normal/random mu sigma cljc/MT)]))
  ([n mu sigma]
   (-> (Normal/random n mu sigma cljc/MT)
       (seq))))

(defn dnorm
  "density(double x, double mu, double sigma, boolean give_log)"
  [x mu sigma give-log]
  (-> [(Normal/density x mu sigma give-log)]
      (seq)))

(defn qnorm
  "quantile(double p, double mu, double sigma, boolean lower_tail, boolean log_p)"
  [p mu sigma lower-tail log-p]
  (-> [(Normal/quantile p mu sigma lower-tail log-p)]
      (seq)))

(defn cnorm
  "cumulative(double x, double mu, double sigma, boolean lower_tail, boolean log_p)"
  [x mu sigma lower-tail log-p]
  (-> [(Normal/cumulative x mu sigma lower-tail log-p)]
      (seq)))
