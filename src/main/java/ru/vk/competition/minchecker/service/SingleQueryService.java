package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SingleQueryService {

    //private final SingleQueryRepository queryRepository;


    public ResponseEntity<Void> addResult(String resultId, String code) {
        try {
            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(code == null || Integer.parseInt(code) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(Integer.parseInt(code) != HttpStatus.BAD_REQUEST.value()) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> addNewQuery(String resultId, String queryId, String query) {
        try {
            if(queryId == null || Integer.parseInt(queryId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
                /*if (queryRepository.findByQueryId(queryId).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                }*/
            //TODO не cуществует такого resuitId
            //TODO what is query and what to do with it...


            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> addModifyResult(String resultId, String code) {
        try {
            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(code == null || Integer.parseInt(code) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(Integer.parseInt(code) != HttpStatus.NOT_ACCEPTABLE.value()) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> modifySingleQuery(String resultId, String queryId, String query) {
        try {
            if(queryId == null || Integer.parseInt(queryId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }/* else if (queryRepository.findByQueryId(queryId).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                }*/

            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }/*  else if () {
                    //TODO Запроса с таким id не существует
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                }*/

            if(query == null || query.isEmpty() || query.isBlank()) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            } else if (query.length() > 120) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Void> addDeleteResult(String resultId, String code) {
        try {
            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(code == null || Integer.parseInt(code) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(Integer.parseInt(code) != HttpStatus.NOT_ACCEPTABLE.value()) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> deleteSingleQueryById(int id, int resid) {
        try {
                /*if (queryRepository.findByQueryId(String.valueOf(id)).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                }*/
            //TODO ● Не существует заданного resultId

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Void> addExecuteResult(String resultId, String code) {
        try {
            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(code == null || Integer.parseInt(code) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(Integer.parseInt(code) != HttpStatus.NOT_ACCEPTABLE.value()) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> executeSingleQueryById(int id, int resid) {
        try {
                /*if (queryRepository.findByQueryId(String.valueOf(id)).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                }*/
            //TODO ● Синтаксис запроса неверный
            //TODO ● Не существует заданного resultId

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Void> addGetSingleQueryByIdResult(String resultId, String code) {
        try {
            if(resultId == null || Integer.parseInt(resultId) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(code == null || Integer.parseInt(code) <= 0) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
            if(Integer.parseInt(code) != HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> getSingleQueryBiId(int id, int resid) {
        try {
                /*if (queryRepository.findByQueryId(String.valueOf(id)).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
                }*/
            //TODO ● Не существует заданного resultId

            //TODO add some work here
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}