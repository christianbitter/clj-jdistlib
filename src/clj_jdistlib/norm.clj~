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
