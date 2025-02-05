(ns hospital2.exer4
  (:use clojure.pprint))

(defrecord PacienteParticular [id, nome, nascimento, situacao])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, situacao, plano])

(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(defn nao-e-urgente? [paciente]
  (not= :urgente (:situacao paciente :normal)))

(extend-type PacienteParticular Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (and (>= valor 50) (nao-e-urgente? paciente))))

(extend-type PacientePlanoDeSaude Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (let [plano (:plano paciente)]
      (and (not (some #(= % procedimento) plano)) (nao-e-urgente? paciente)))))


(let [particular (->PacienteParticular 15, "Miguel", "11/04/06", :normal)
      plano (->PacientePlanoDeSaude 15, "Miguel", "1/04/06", :normal [:raio-x, :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 500))
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 40))
  (pprint (deve-assinar-pre-autorizacao? plano, :raio-x, 499990))
  (pprint (deve-assinar-pre-autorizacao? plano, :coleta-de-sangue, 499990)))


(let [particular (->PacienteParticular 15, "Miguel", "11/04/06", :urgente)
      plano (->PacientePlanoDeSaude 15, "Miguel", "1/04/06", :urgente [:raio-x, :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 500))
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 40))
  (pprint (deve-assinar-pre-autorizacao? plano, :raio-x, 499990))
  (pprint (deve-assinar-pre-autorizacao? plano, :coleta-de-sangue, 499990)))


(defmulti assinar-pre-autorizacao? class)

(defmethod assinar-pre-autorizacao? PacienteParticular [paciente]
  (println "Invocando paciente particular") true)

(defmethod assinar-pre-autorizacao? PacientePlanoDeSaude [paciente]
  (println "Invocando paciente plano de saúde") false)

(let [particular (->PacienteParticular 15, "Miguel", "11/04/06", :urgente)
      plano (->PacientePlanoDeSaude 15, "Miguel", "1/04/06", :urgente [:raio-x, :ultra-som])]
  (pprint (assinar-pre-autorizacao? particular))
  (pprint (assinar-pre-autorizacao? plano)))

