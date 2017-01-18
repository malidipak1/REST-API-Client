package org.igniterealtime.restclient;

import javax.ws.rs.core.Response;
import org.igniterealtime.restclient.RestClient.RestClientBuilder;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.GroupEntities;
import org.igniterealtime.restclient.entity.GroupEntity;
import org.igniterealtime.restclient.entity.MUCRoomEntities;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.ParticipantEntities;
import org.igniterealtime.restclient.entity.RosterEntities;
import org.igniterealtime.restclient.entity.RosterItemEntity;
import org.igniterealtime.restclient.entity.SessionEntities;
import org.igniterealtime.restclient.entity.SystemProperties;
import org.igniterealtime.restclient.entity.SystemProperty;
import org.igniterealtime.restclient.entity.UserEntities;
import org.igniterealtime.restclient.entity.UserEntity;
import org.igniterealtime.restclient.entity.UserGroupsEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class RestApiClient.
 */
public class RestApiClient {

    /** The rest client. */
    private RestClient restClient;

    /**
     * Instantiates a new rest api client.
     *
     * @param url
     *            the url
     * @param port
     *            the port
     * @param authenticationToken
     *            the authentication token
     */
    public RestApiClient(String url, int port, AuthenticationToken authenticationToken) {
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        restClient = new RestClientBuilder(url + ":" + port).authenticationToken(authenticationToken)
                .connectionTimeout(5000).build();
    }

    /**
     * Gets the users.
     *
     * @return the users
     */
    public UserEntities getUsers() {
        UserEntities userEntities = restClient.get("users", UserEntities.class, new HashMap<String, String>());
        return userEntities;
    }

    /**
     * Gets the users.
     *
     * @param queryParams
     *            the query params
     * @return the users
     */
    public UserEntities getUsers(Map<String, String> queryParams) {
        UserEntities userEntities = restClient.get("users", UserEntities.class, queryParams);
        return userEntities;
    }

    /**
     * Gets the user.
     *
     * @param username
     *            the username
     * @return the user
     */
    public UserEntity getUser(String username) {
        UserEntity userEntity = restClient.get("users/" + username, UserEntity.class, new HashMap<String, String>());
        return userEntity;
    }

    /**
     * Creates the user.
     *
     * @param userEntity
     *            the user entity
     * @return the response
     */
    public Response createUser(UserEntity userEntity) {
        return restClient.post("users", userEntity, new HashMap<String, String>());
    }

    /**
     * Update user.
     *
     * @param userEntity
     *            the user entity
     * @return the response
     */
    public Response updateUser(UserEntity userEntity) {
        return restClient.put("users/" + userEntity.getUsername(), userEntity, new HashMap<String, String>());
    }

    /**
     * Delete user.
     *
     * @param username
     *            the username
     * @return the response
     */
    public Response deleteUser(String username) {
        return restClient.delete("users/" + username, new HashMap<String, String>());
    }

    /**
     * Gets the chat rooms.
     *
     * @return the chat rooms
     */
    public MUCRoomEntities getChatRooms() {
        return getChatRooms(new HashMap<String, String>());
    }

    /**
     * Gets the chat rooms.
     *
     * @param queryParams
     *            the query params
     * @return the chat rooms
     */
    public MUCRoomEntities getChatRooms(Map<String, String> queryParams) {
        return restClient.get("chatrooms", MUCRoomEntities.class, queryParams);
    }
    public MUCRoomEntities getChatRooms(String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return getChatRooms(queryParams);
        //return restClient.get("chatrooms", MUCRoomEntities.class, queryParams);
    }
    /**
     * Gets the chat room.
     *
     * @param roomName
     *            the room name
     * @return the chat room
     */
    public MUCRoomEntity getChatRoom(String roomName) {
        return getChatRoom(roomName, new HashMap<String, String>());
    }

    public MUCRoomEntity getChatRoom(String roomName, Map<String, String> queryParams) {
        return restClient.get("chatrooms/" + roomName, MUCRoomEntity.class, queryParams);
    }

    public MUCRoomEntity getChatRoom(String roomName, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return getChatRoom(roomName, queryParams);
    }

    /**
     * Creates the chat room.
     *
     * @param chatRoom
     *            the chat room
     * @return the response
     */
    public Response createChatRoom(MUCRoomEntity chatRoom) {
        return createChatRoom(chatRoom, new HashMap<String, String>());
    }

    public Response createChatRoom(MUCRoomEntity chatRoom, Map<String, String> queryParams) {
        return restClient.post("chatrooms", chatRoom, queryParams);
    }

    public Response createChatRoom(MUCRoomEntity chatRoom, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return createChatRoom(chatRoom, queryParams);
    }

    /**
     * Update chat room.
     *
     * @param chatRoom
     *            the chat room
     * @return the response
     */
    public Response updateChatRoom(MUCRoomEntity chatRoom) {
        return updateChatRoom( chatRoom, new HashMap<String, String>());
    }

    public Response updateChatRoom(MUCRoomEntity chatRoom, Map<String, String> queryParams) {
        return restClient.put("chatrooms/" + chatRoom.getRoomName(), chatRoom, queryParams);
    }

    public Response updateChatRoom(MUCRoomEntity chatRoom, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return updateChatRoom(chatRoom, queryParams);
    }

    /**
     * Delete chat room.
     *
     * @param roomName the room name
     * @return the response
     */
    public Response deleteChatRoom(String roomName) {
        return deleteChatRoom( roomName, new HashMap<String, String>());
    }

    public Response deleteChatRoom(String roomName, Map<String, String> queryParams) {
        return restClient.delete("chatrooms/" + roomName, queryParams);
    }

    public Response deleteChatRoom(String roomName, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return deleteChatRoom(roomName, queryParams);
    }

    /**
     * Gets the chat room participants.
     *
     * @param roomName
     *            the room name
     * @return the chat room participants
     */
    public ParticipantEntities getChatRoomParticipants(String roomName) {
        return getChatRoomParticipants( roomName, new HashMap<String, String>());
    }

    public ParticipantEntities getChatRoomParticipants(String roomName, Map<String, String> queryParams) {
        return restClient.get("chatrooms/" + roomName + "/participants", ParticipantEntities.class,
                queryParams);
    }

    public ParticipantEntities getChatRoomParticipants(String roomName, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return getChatRoomParticipants(roomName, queryParams);
    }

    /**
     * Adds the owner.
     *
     * @param roomName the room name
     * @param jid the jid
     * @return the response
     */
    public Response addOwner(String roomName, String jid) {
        return addOwner(roomName, jid, new HashMap<String, String>());
    }

    public Response addOwner(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.post("chatrooms/" + roomName + "/owners/" + jid, null, queryParams);
    }

    public Response addOwner(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return addOwner(roomName, jid, queryParams) ;
    }

    public Response deleteOwner(String roomName, String jid) {
        return deleteOwner(roomName, jid, new HashMap<String, String>());
    }

    public Response deleteOwner(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.delete("chatrooms/" + roomName + "/owners/" + jid, queryParams);
    }

    public Response deleteOwner(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return deleteOwner(roomName, jid, queryParams);
    }
    /**
     * Adds the admin.
     *
     * @param roomName the room name
     * @param jid the jid
     * @return the response
     */
    public Response addAdmin(String roomName, String jid) {
        return addAdmin(roomName, jid, new HashMap<String, String>());
    }
    public Response addAdmin(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.post("chatrooms/" + roomName + "/admins/" + jid, null, queryParams);
    }
    public Response addAdmin(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return addAdmin(roomName, jid, queryParams);
        //return restClient.post("chatrooms/" + roomName + "/admins/" + jid, null, new HashMap<String, String>());
    }

    public Response deleteAdmin(String roomName, String jid) {
        return deleteAdmin(roomName, jid, new HashMap<String, String>());
    }
    public Response deleteAdmin(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.delete("chatrooms/" + roomName + "/admins/" + jid, queryParams);
    }
    public Response deleteAdmin(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return deleteAdmin(roomName, jid, queryParams);
        //return restClient.delete("chatrooms/" + roomName + "/admins/" + jid, new HashMap<String, String>());
    }
    /**
     * Adds the member.
     *
     * @param roomName the room name
     * @param jid the jid
     * @return the response
     */
    public Response addMember(String roomName, String jid) {
        return addMember(roomName, jid, new HashMap<String, String>());
    }
    public Response addMember(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.post("chatrooms/" + roomName + "/members/" + jid, null, queryParams);
    }
    public Response addMember(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return addMember(roomName, jid, queryParams);
        //return restClient.post("chatrooms/" + roomName + "/members/" + jid, null, new HashMap<String, String>());
    }

    public Response deleteMember(String roomName, String jid) {
        return deleteMember(roomName, jid, new HashMap<String, String>());
    }
    public Response deleteMember(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.delete("chatrooms/" + roomName + "/members/" + jid, queryParams);
    }
    public Response deleteMember(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return deleteMember(roomName, jid, queryParams);
        // return restClient.delete("chatrooms/" + roomName + "/members/" + jid, queryParams);
    }

    /**
     * Adds the outcast.
     *
     * @param roomName the room name
     * @param jid the jid
     * @return the response
     */
    public Response addOutcast(String roomName, String jid) {
        return addOutcast(roomName, jid, new HashMap<String, String>());
    }
    public Response addOutcast(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.post("chatrooms/" + roomName + "/outcasts/" + jid, null, queryParams);
    }
    public Response addOutcast(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return addOutcast(roomName, jid, queryParams);
        //return restClient.post("chatrooms/" + roomName + "/outcasts/" + jid, null, new HashMap<String, String>());
    }

    public Response deleteOutcast(String roomName, String jid) {
        return deleteOutcast(roomName, jid, new HashMap<String, String>());
    }
    public Response deleteOutcast(String roomName, String jid, Map<String, String> queryParams) {
        return restClient.delete("chatrooms/" + roomName + "/outcasts/" + jid, queryParams);
    }
    public Response deleteOutcast(String roomName, String jid, String servicename) {
        Map<String, String> queryParams = new HashMap<String, String>();
        if(servicename != null) {
            queryParams.put("servicename", servicename);
        }
        return deleteOutcast(roomName, jid, queryParams);
        //return restClient.delete("chatrooms/" + roomName + "/outcasts/" + jid, new HashMap<String, String>());
    }	/**
     * Gets the sessions.
     *
     * @return the sessions
     */
    public SessionEntities getSessions() {
        SessionEntities sessionEntities = restClient.get("sessions", SessionEntities.class,
                new HashMap<String, String>());
        return sessionEntities;
    }

    /**
     * Gets the sessions.
     *
     * @param username
     *            the username
     * @return the sessions
     */
    public SessionEntities getSessions(String username) {
        SessionEntities sessionEntities = restClient.get("sessions/" + username, SessionEntities.class,
                new HashMap<String, String>());
        return sessionEntities;
    }

    /**
     * Gets the user groups.
     *
     * @param username the username
     * @return the user groups
     */
    public UserGroupsEntity getUserGroups(String username) {
        return restClient.get("users/" + username + "/groups", UserGroupsEntity.class,
                new HashMap<String, String>());
    }

    /**
     * Adds the user to groups.
     *
     * @param username the username
     * @param userGroupsEntity the user groups entity
     * @return the response
     */
    public Response addUserToGroups(String username, UserGroupsEntity userGroupsEntity) {
        return restClient.post("users/" + username + "/groups/", userGroupsEntity,
                new HashMap<String, String>());
    }

    /**
     * Adds the user to group.
     *
     * @param username the username
     * @param groupName the group name
     * @return the response
     */
    public Response addUserToGroup(String username, String groupName) {
        return restClient.post("users/" + username + "/groups/"+ groupName, null,
                new HashMap<String, String>());
    }

    /**
     * Delete user from group.
     * @param username the username
     * @param groupName the group name
     * @return the response
     */
    public Response deleteUserFromGroup(String username, String groupName) {
        return restClient.delete("users/" + username + "/groups/" + groupName,
                new HashMap<String, String>());
    }


    /**
     * Lockout user.
     * @param username the username
     * @return the response
     */
    public Response lockoutUser(String username) {
        return restClient.post("lockouts/" + username, null, new HashMap<String, String>());
    }

    /**
     * Unlock user.
     * @param username the username
     * @return the response
     */
    public Response unlockUser(String username) {
        return restClient.delete("lockouts/" + username, new HashMap<String, String>());
    }

    /**
     * Gets the system properties.
     * @return the system properties
     */
    public SystemProperties getSystemProperties() {
        return restClient.get("system/properties", SystemProperties.class,
                new HashMap<String, String>());
    }

    /**
     * Gets the system property.
     * @param propertyName the property name
     * @return the system property
     */
    public SystemProperty getSystemProperty(String propertyName) {
        return restClient.get("system/properties/" + propertyName, SystemProperty.class,
                new HashMap<String, String>());
    }

    /**
     * Creates the system property.
     * @param property  the property
     * @return the response
     */
    public Response createSystemProperty(SystemProperty property) {
        return restClient.post("system/properties", property, new HashMap<String, String>());
    }

    /**
     * Update system property.
     * @param property  the property
     * @return the response
     */
    public Response updateSystemProperty(SystemProperty property) {
        return restClient.put("system/properties/" + property.getKey(), property, new HashMap<String, String>());
    }

    /**
     * Delete system property.
     * @param propertyName  the property name
     * @return the response
     */
    public Response deleteSystemProperty(String propertyName) {
        return restClient.delete("system/properties/" + propertyName, new HashMap<String, String>());
    }

    /**
     * Gets the groups.
     * @return the groups
     */
    public GroupEntities getGroups() {
        return restClient.get("groups", GroupEntities.class, new HashMap<String, String>());
    }

    /**
     * Gets the group.
     * @param groupName  the group name
     * @return the group
     */
    public GroupEntity getGroup(String groupName) {
        return restClient.get("groups/" + groupName, GroupEntity.class, new HashMap<String, String>());
    }

    /**
     * Creates the group.
     * @param group  the group
     * @return the response
     */
    public Response createGroup(GroupEntity group) {
        return restClient.post("groups", group, new HashMap<String, String>());
    }

    /**
     * Update group.
     * @param group the group
     * @return the response
     */
    public Response updateGroup(GroupEntity group) {
        return restClient.put("groups/" + group.getName(), group, new HashMap<String, String>());
    }

    /**
     * Delete group.
     * @param groupName the group name
     * @return the response
     */
    public Response deleteGroup(String groupName) {
        return restClient.delete("groups/" + groupName, new HashMap<String, String>());
    }

    /**
     * Gets the roster.
     * @param username the username
     * @return the roster
     */
    public RosterEntities getRoster(String username) {
        return restClient.get("users/" + username + "/roster", RosterEntities.class, new HashMap<String, String>());
    }

    /**
     * Adds the roster entry.
     * @param username the username
     * @param rosterItemEntity the roster item entity
     * @return the response
     */
    public Response addRosterEntry(String username, RosterItemEntity rosterItemEntity) {
        return restClient.post("users/" + username + "/roster", rosterItemEntity, new HashMap<String, String>());
    }

    /**
     * Update roster entry.
     * @param username the username
     * @param rosterItemEntity the roster item entity
     * @return the response
     */
    public Response updateRosterEntry(String username, RosterItemEntity rosterItemEntity) {
        return restClient.put("users/" + username + "/roster/" + rosterItemEntity.getJid(), rosterItemEntity,
                new HashMap<String, String>());
    }

    /**
     * Delete roster entry.
     * @param username the username
     * @param jid the jid
     * @return the response
     */
    public Response deleteRosterEntry(String username, String jid) {
        return restClient.delete("users/" + username + "/roster/" + jid, new HashMap<String, String>());
    }

    /**
     * Gets the rest client.
     * @return the rest client
     */
    public RestClient getRestClient() {
        return restClient;
    }

}
