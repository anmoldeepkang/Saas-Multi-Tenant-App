import React, { Component } from "react";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/Card';
import EditIcon from '@material-ui/icons/Edit';
import { IconButton } from '@material-ui/core';
import Grid from "@material-ui/core/Grid";
import DeleteIcon from '@material-ui/icons/Delete';
import Dialog from "@material-ui/core/Dialog";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import { TextField } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import Slide from '@material-ui/core/Slide';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogActions from '@material-ui/core/DialogActions';

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});


class Message extends Component {

  constructor(props) {
    super(props);
    this.state={
      message:this.props.message,
      editDialogOpen:false,
      deleteDialogOpen:false
    }
  }
  componentDidMount() {

  }
  handleEdit=()=>{
    this.setState({editDialogOpen:true})
  }
  handleDeleteClick=()=>{
    this.setState({deleteDialogOpen:true})
  }
  handleDeleteClose=()=>{
    this.setState({deleteDialogOpen:false})
  }
  handleEditClose=()=>{
    this.setState({editDialogOpen:false})
  }
  handleUpdate=(event)=>{
    event.preventDefault();
    this.setState({editDialogOpen:false});
    //Make PUT call to update message here
    console.log(this.state.message);

  }
  handleDeleteConfirm=()=>{
    this.setState({deleteDialogOpen:false});
    //Make DELETE call to delete message here
    console.log(this.state.message);
    let msg=this.state.message;
    msg.message="";
    this.setState({message:msg});
  }
  handleChange=(event)=>{
    let msg=this.state.message;
    msg.message=event.target.value;
    this.setState({message:msg});
  }
    render() {
      const {message}=this.props.message;
      let closeImg = {cursor:'pointer', float:'right', marginTop: '5px', width: '20px'};
      return message!=="" ? (
        <div className="message123">
        <Card className="messageCard">
          <CardContent>
          <Grid container direction="row">
            <Grid item style={{width:'86%'}}>
              <p className="messageText">{message}</p>

            </Grid>
            <Grid item style={{width:'7%'}}>
              <IconButton><EditIcon onClick={this.handleEdit}/></IconButton>
            </Grid>
            <Grid item style={{width:'7%'}}>
              <IconButton><DeleteIcon onClick={this.handleDeleteClick}/></IconButton>
            </Grid>
            <img src={this.props.message.image} style={{width:'80%',margin:'10px 20px 20px 60px'}} />
            <Dialog
              open={this.state.editDialogOpen}
              fullWidth
              maxWidth="sm"
            >

              <DialogTitle>
                <div>Update a message
                  <img src='https://d30y9cdsu7xlg0.cloudfront.net/png/53504-200.png' style={closeImg} onClick={this.handleEditClose}/>
                </div>

              </DialogTitle>
              <DialogContent>
                <form onSubmit={this.handleUpdate}>
                  <TextField
                    name="body"
                    type="text"
                    label="MESSAGE"
                    multiline
                    rows="3"
                    placeholder="Message"
                    onChange={this.handleChange}
                    fullWidth
                    value={this.state.message.message}
                  />
                  <Button
                    style={{marginTop:'20px'}}
                    type="submit"
                    variant="contained"
                    color="primary"

                  >
                    Update

                  </Button>
                </form>
                <div className={(this.state.message.image!==undefined)?'previewImg':'dummy'}>
                 <img src={this.state.message.image} style={{maxWidth:'100%',maxHeight:'100%',margin:'20px auto auto 10px'}} />
                 </div>
              </DialogContent>
            </Dialog>

            <Dialog
                fullWidth
                open={this.state.deleteDialogOpen}
                TransitionComponent={Transition}
                keepMounted
                onClose={this.handleDeleteClose}
                aria-labelledby="alert-dialog-slide-title"
                aria-describedby="alert-dialog-slide-description"
              >
                <DialogTitle id="alert-dialog-slide-title">
                <div>Delete a message ?
                  <img src='https://d30y9cdsu7xlg0.cloudfront.net/png/53504-200.png' style={closeImg} onClick={this.handleDeleteClose}/>
                </div>
                </DialogTitle>
                <DialogContent>
                  <DialogContentText id="alert-dialog-slide-description">
                    {this.state.message.message}
                  </DialogContentText>
                </DialogContent>
                <DialogActions>

                  <Button onClick={this.handleDeleteConfirm} color="primary">
                    Confirm
                  </Button>
                </DialogActions>
              </Dialog>
          </Grid>

          </CardContent>
        </Card>


        </div>

      ):""
    }

}

export default Message;
