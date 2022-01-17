package com.chan.chang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.AlbumMapper;
import com.chan.chang.goods.pojo.Album;
import com.chan.chang.goods.service.AlbumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    final
    AlbumMapper albumMapper;

    public AlbumServiceImpl(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    @Override
    public IPage<Album> findAlbumPageByCondition(Album album, int page, int size) {
        Page<Album> albumPage = new Page<>(page, size);
        if (album == null) {
            return albumMapper.selectPage(albumPage, null);
        } else {
            Wrapper<Album> wrapper = createWrapper(album);
            return albumMapper.selectPage(albumPage, wrapper);
        }
    }

    @Override
    public IPage<Album> findAlbumPage(int page, int size) {
        Page<Album> albumPage = new Page<>(page, size);
        return albumMapper.selectPage(albumPage, null);
    }

    @Override
    public List<Album> findAlbumByCondition(Album album) {
        Wrapper<Album> wrapper = createWrapper(album);
        return albumMapper.selectList(wrapper);
    }

    @Override
    public void deleteById(Long id) {
        albumMapper.deleteById(id);
    }

    @Override
    public void updateAlbum(Long id, Album album) {
        album.setId(id);
        albumMapper.updateById(album);
    }

    @Override
    public void addAlbum(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public List<Album> findAlbum() {
        return albumMapper.selectList(null);
    }

    @Override
    public Album findById(Long id) {
        return albumMapper.selectById(id);
    }

    public Wrapper<Album> createWrapper(Album album) {
        QueryWrapper<Album> wrapper = new QueryWrapper<>();
        if (album != null) {
            if (album.getId() != null && album.getId() != 0) {
                wrapper.eq("id", album.getId());
            }
            if (!StringUtils.isEmpty(album.getTitle())) {
                wrapper.like("title", album.getTitle());
            }
            if (!StringUtils.isEmpty(album.getImage())) {
                wrapper.eq("image", album.getImage());
            }
            if (!StringUtils.isEmpty(album.getImageItems())) {
                wrapper.eq("image_items", album.getImageItems());
            }
        }
        return wrapper;
    }
}
