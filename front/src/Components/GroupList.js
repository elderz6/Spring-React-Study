import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';

class GroupList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      groups:[],
      isLoading: true
    };
  }
  async componentDidMount(){
    this.setState({
      isLoading:true
    });
    axios.get('/api/groups')
      .then(res => this.setState({
        groups: res.data,
        isLoading: false
      }));
  }

  async remove(id){
    await axios.delete(`/api/group/${id}`)
      .then(() => {
        let updatedGroups = [...this.state.groups]
          .filter(i => i.id !== id);
        this.setState({
          groups:updatedGroups
        });
      });
  }


  render() {
    const { groups, isLoading} = this.state;
    return (
      <div>
        {groups.map(group =>
          <div key={group.id}>
            {group.name}
          </div>)}
      </div>
    );
  }

}

export default GroupList;
