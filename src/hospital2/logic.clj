(ns hospital2.logic
  (:require [hospital2.model :as h.model]))

(defn agora []
  (h.model/to-ms (java.util.Date.)))
