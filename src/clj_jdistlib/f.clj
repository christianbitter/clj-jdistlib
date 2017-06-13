(in-ns 'clj-jdistlib.dist)

(import '(net.sourceforge.jdistlib F))

;(require '[clj-jdistlib.common :as cljc])

(defn rf
  "generate sample from F distribution"
  ([df1 df2] (F/random df1 df2 cljc/MT))
  ([n df1 df2]
   (-> (F/random n df1 df2 cljc/MT)
       (seq))))
