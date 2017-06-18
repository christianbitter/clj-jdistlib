(ns clj-jdistlib.core
  (:gen-class))

(require '[clj-jdistlib.dist :as cljd]
         '[clj-jdistlib.stat :as cljs])

(use 'clojure.repl)

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
                     
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (str "Hello, World: " cljd/rnorm))
