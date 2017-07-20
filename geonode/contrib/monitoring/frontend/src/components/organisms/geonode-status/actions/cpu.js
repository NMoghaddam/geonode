import { createAction } from 'redux-actions';
import { fetch, formatApiDate, sequenceInterval } from '../../../../utils';
import apiUrl from '../../../../backend';
import { GEONODE_CPU_SEQUENCE } from '../constants';


const reset = createAction(
  GEONODE_CPU_SEQUENCE,
  () => ({ status: 'initial' })
);


export const begin = createAction(
  GEONODE_CPU_SEQUENCE,
  () => ({ status: 'pending' })
);


const success = createAction(
  GEONODE_CPU_SEQUENCE,
  response => ({
    response,
    status: 'success',
  })
);


const fail = createAction(
  GEONODE_CPU_SEQUENCE,
  error => ({
    status: 'error',
    error,
  })
);


const get = (from, to) =>
  (dispatch, getState) => {
    dispatch(begin());
    const formatedFrom = formatApiDate(from);
    const formatedTo = formatApiDate(to);
    const interval = sequenceInterval(getState);
    let url = `${apiUrl}/metric_data/cpu.usage.percent/?valid_from=${formatedFrom}`;
    url += `&valid_to=${formatedTo}&interval=${interval}`;
    fetch({ url })
      .then(response => {
        dispatch(success(response));
        return response;
      })
      .catch(error => {
        dispatch(fail(error.message));
      });
  };

const actions = {
  reset,
  begin,
  success,
  fail,
  get,
};

export default actions;
