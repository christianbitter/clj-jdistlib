(in-ns 'clj-jdistlib.dist)

(import
 '(net.sourceforge.jdistlib T))
 
(defn rt
  "
  # rt
  constructs a sample of xi distributed according to a t distribution
  "
  ([df] (seq [(T/random df cljc/MT)]))
  ([n df]
   (-> (T/random n df cljc/MT)
       (seq))))

(defn dt
  "
  # dt
  density(double x, double n, boolean give_log)
  "
  [x df & {:keys [give-log]
           :or {give-log false}}]
  (-> [(T/density x df give-log)]
      (seq)))

(defn qt
  "
  # qt
  quantile(double p, double ndf, boolean lower_tail, boolean log_p)
  "
  [p ndf & {:keys [lower-tail log-p]
            :or {lower-tail false
                 log-p false}}]
  (-> [(T/quantile p ndf lower-tail log-p)]
      (seq)))

(defn ct
  "
  # ct
  cumulative(double x, double n, boolean lower_tail, boolean log_p)
  "
  [x df & {:keys [lower-tail log-p]
           :or {lower-tail false
                log-p false}}]
  (-> [(T/cumulative x df lower-tail log-p)]
      (seq)))
