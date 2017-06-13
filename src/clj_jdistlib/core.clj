(ns clj-jdistlib.core
  (:gen-class))

(require '[clj-jdistlib.dist :as cljd])


(import '(net.sourceforge.jdistlib Ansari Arcsine Beta BetaBinomial
          BetaPrime Binomial Cauchy Chi ChiSquare
          Exponential Gamma Geometric HyperGeometric
          InvGamma InvNormal Kendall
          Kumaraswamy Laplace Logarithmic Logistic LogNormal Nakagami
          NegBinomial NonCentralBeta NonCentralChiSquare NonCentralF
          NonCentralT Normal Poisson SignRank
          Spearman T Tukey Uniform Weibull Wilcoxon Wishart Zipf ))


(defprotocol statistic
  (compute [avec] "compute the particular statistic")


(defn sd
  "sd of vector"
  [avec] 0)

(defn median
  "median of vector"
  [avec] 0)

(defn var
  "variance of vector"
  [avec])


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (str "Hello, World: " cljd/rnorm))
