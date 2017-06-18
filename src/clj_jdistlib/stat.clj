(ns clj-jdistlib.stat)

(require
 '[clj-jdistlib.common :as cljc]
 '[clj-jdistlib.dist :as cljd])

(defn mean
  "mean of vector"
  [avec]
  (let [n (count avec)
        ninv (/ 1 n)
        s (->> avec
               (reduce +))]
    (* ninv s)))


(defn sample-mean
  "sample mean of vector"
  [avec]
  (mean avec))

(defn variance
  "variance of vector"
  [avec]
  (let [n (count avec)
        mu (mean avec)
        ninv (/ 1 n)
        ssx (->> avec
                 (map #(- %1 mu))
                 (map #(* %1 %1))
                 (reduce +)
                 (* ninv))]
    ssx))

(defn sample-variance
  "sample variance of vector"
  [avec]
  (let [n (count avec)
        mu (mean avec)
        ninv (/ 1 (dec n))
        ssx (->> avec
                 (map #(- %1 mu))
                 (map #(* %1 %1))
                 (reduce +)
                 (* ninv))]
    ssx))

(defn ss
  "the sum of squares of a vector is the elements - the mean squared and summed"
  [avec]
  (let [mu (mean avec)]
    (->> avec
         (map #((cljc/sqr (- %2 mu)))))))

(defn pooled-variance
  "compute the pooled variance for two samples y1 and y2 where we assume that y1 and y2
  have the same population variance s, i.e. we use the combined sample variances as
  an estimate of the common population variance"
  [y1 y2]
  (let [n1 (count y1)        
        n2 (count y2)
        dn1 (dec n1)
        dn2 (dec n2)
        dn (+ dn1 dn2)
        s1 (sample-variance y1)
        s2 (sample-variance y2)]
    {:pooled-variance (/ (+ (* dn1 s1) (* dn2 s2)) dn)
     :sample-variance-y1 s1
     :no-samples-y1 n1     
     :sample-variance-y2 s2
     :no-samples-y2 n2
     :no-samples-combined (+ n1 n2)}))

(defn pooled-sd
  "compute the pooled standard deviation based of the pooled variance"
  [y1 y2]
  (let [pv (pooled-variance y1 y2)
        pooled-sd (cljc/sqrt (pv :pooled-variance))]
    (-> pv
        (assoc :pooled-sd pooled-sd))))

(defn sd
  "sd of vector - second moment around the mean"
  [avec]
  (Math/sqrt (variance avec)))

(defn sample-sd
  "sample standard deviation of vector"
  [avec]
  (Math/sqrt (sample-variance avec)))

(defn- t-statistic-equal-pooled-variance
  "compute the t0 statistic for y1 and y2
   under the asspumption assumption that variance is equal - pooled variance"
  [y1 y2]
  (let [mu1 (mean y1)
        mu2 (mean y2)
        dmu (- mu1 mu2)
        n1 (count y1)
        n2 (count y2)
        n1inv (/ 1 n1)
        n2inv (/ 1 n2)
        sninv (+ n1inv n2inv)
        s-pooled (pooled-sd y1 y2)
        t0 (/ dmu (* (s-pooled :pooled-sd) (cljc/sqrt sninv)))]
    (-> s-pooled
        (assoc :t0 t0
               :estimate-of-difference dmu))))

;;R
;;t.test(x, y = NULL,
;;       alternative = c("two.sided", "less", "greater"),
;;       mu = 0, paired = FALSE, var.equal = FALSE,
;;       conf.level = 0.95, ...)

(defn two-sample-t-test
  "
  # Two-Sample-T-Test
  ----
  The function performs a two-sample t-test, where we compare the means of two samples x and y
  against each other. The method assumes that the two samples share a common variance, and 
  that the two samples are relatively balanced in terms of sample size.
  The pooled variance is used to estimate the common variance.

  Required Parameters:
  * x - sample one
  * y - sample two
  Optional Parameters:
  * alternative -  character string specifying the alternative hypothesis,
                   must be one of \"two.sided\" (default), \"greater\" or \"less\".
                   You can specify just the initial letter.
  * confidence-level

  Returns:
  The result of performing the two-sample t-test
  "
  [x y & {:keys [alternative confidence-level]
          :or {alternative "two.sided"
               confidence-level 0.05}}]
  (let [t0 (t-statistic-equal-pooled-variance x y)
        alpha (if (= alternative "two.sided")
                     (/ confidence-level 2)
                     confidence-level)
        lower-tail (if (= alternative "two.sided")
                     true
                     false)
        p (- 1 alpha)
        df (- (t0 :no-samples-combined) 2)
        t (cljd/qt p df lower-tail false)
        ;;todo: compute the missing values like p-value and test-result
        p-value 0
        test-result "missing"]        
    (-> t0
        (assoc :confidence-level confidence-level
               :alternative alternative
               :t-reference t
               :degree-of-freedom df2
               :confidence-level p
               :alpha alpha
               :variance-estimation "assuming equal variance"
               :test-result test-result
               :p-value p-value))))
