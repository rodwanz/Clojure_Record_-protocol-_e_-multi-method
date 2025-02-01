(ns hospital2.exer1
  (:use clojure.pprint))

(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente não possui id" {:áciente paciente}))))


(defn chamando-pacientes []
  (let [pacientes {}
        miguel { :id 15 :nome "Miguel" :nascimento "11/04/06"}
        pedro { :id 20 :nome "Pedro" :nascimento "06/08/04"}
        mateus { :nome "Mateus" :nascimento "23/05/03"}
        ]
    (pprint (adiciona-paciente pacientes miguel))
    (pprint (adiciona-paciente pacientes pedro))
    (pprint (adiciona-paciente pacientes mateus))))


(chamando-pacientes)


