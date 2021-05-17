package io.github.quickmsg.system.service.impl;

import com.smqtt.common.core.constant.UserConstants;
import com.smqtt.system.api.domain.SysRole;
import com.smqtt.system.api.domain.SysUser;
import io.github.quickmsg.system.mapper.SysUserMapper;
import io.github.quickmsg.system.service.ISysMenuService;
import io.github.quickmsg.system.service.ISysPermissionService;
import io.github.quickmsg.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService
{
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }

    /**
     * 获取可操作权限的用户登录名数据
     *
     * @param userName 登录账号
     */
    @Override
    public Set<String> getUserNamePermission(String userName) {
        Set<String> names = new HashSet<>();
        //查询当前用户信息
        SysUser sysUser = sysUserMapper.selectUserByUserName(userName);
        //获取当前登录用户的角色id集合
        Long[] roles = sysUser.getRoles().stream().map(SysRole::getRoleId).toArray(Long[]::new);
        for (SysRole role : sysUser.getRoles()) {
            if (UserConstants.DATA_SCOPE_ALL.equals(role.getDataScope())) {
                //所有数据权限    存入admin
                names.add(UserConstants.ADMIN_USER);
            } else  if (UserConstants.DATA_SCOPE_CUSTOM.equals(role.getDataScope())) {
                //自定义数据权限
                names.addAll(new HashSet<>(sysUserMapper.selectAuthListByCustom(userName, roles)));
            }
            else  if (UserConstants.DATA_SCOPE_DEPT.equals(role.getDataScope())) {
                //部门数据权限
                names.addAll(new HashSet<>(sysUserMapper.selectAuthListByDept(userName, sysUser.getDeptId())));
            } else  if (UserConstants.DATA_SCOPE_DEPT_AND_CHILD.equals(role.getDataScope())) {
                //部门及以下数据权限
                names.addAll(new HashSet<>(sysUserMapper.selectAuthListByDeptAndChild(userName, sysUser.getDeptId())));
            } else {
                //仅本人数据权限   存入自己
                names.add(userName);
            }
        }
        return names;
    }
}
