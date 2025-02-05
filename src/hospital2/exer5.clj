(ns hospital2.exer5
  (:use clojure.pprint))

(defn tipo-de-autorizador [pedido]
  (let [paciente (:paciente pedido)
        situacao (:situacao paciente)]
    (cond (= :urgente situacao) :sempre-autorizado
          (contains? paciente :plano)
          :plano-de-saude :else :credito-minimo)))

(defmulti deve-assinar-pre-autorizacao? tipo-de-autorizador)

(defmethod deve-assinar-pre-autorizacao? :sempre-autorizado [pedido]
  false)
(defmethod deve-assinar-pre-autorizacao? :plano-de-saude [pedido]
  (not (some #(= % (:procedimento pedido)) (:plano(:paciente pedido)))))

(defmethod deve-assinar-pre-autorizacao? :credito-minimo [pedido]
  (>= (:valor pedido 0) 50))


(let [particular {:id 5, :nome "Miguel", :nascimento "11/04/06", :situacao :urgente}
      plano {:id 15, :nome "Miguel", :nascimento "11/04/06", :situacao :urgente :plano [:raio-x, :ultra-som]}]
  (pprint (deve-assinar-pre-autorizacao? {:paciente particular, :valor 1000, :procedimento :coleta-de-sangue}))
  (pprint (deve-assinar-pre-autorizacao? {:paciente plano, :valor 1000, :procedimento :coleta-de-sangue})))


(let [particular {:id 5, :nome "Miguel", :nascimento "11/04/06", :situacao :normal}
      plano {:id 15, :nome "Miguel", :nascimento "11/04/06", :situacao :normal :plano [:raio-x, :ultra-som]}]
  (pprint (deve-assinar-pre-autorizacao? {:paciente particular, :valor 1000, :procedimento :coleta-de-sangue}))
  (pprint (deve-assinar-pre-autorizacao? {:paciente plano, :valor 1000, :procedimento :coleta-de-sangue})))



