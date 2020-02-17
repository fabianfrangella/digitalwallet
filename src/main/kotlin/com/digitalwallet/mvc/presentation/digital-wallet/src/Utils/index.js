

export const isLogin = (props) => {
    let userId = props.location.state !== undefined ? props.location.state.userId : '';
    return userId !== null && userId !== undefined && userId !== '';
};